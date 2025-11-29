package com.fed.mivivienda.service;

import com.fed.mivivienda.entity.Loan;
import com.fed.mivivienda.entity.PaymentScheduleItem;
import com.fed.mivivienda.util.RateUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class FinancialService {

    // Método francés vencido ordinario (mes de 30 días -> manejamos como mensual fijo)
    public List<PaymentScheduleItem> generateSchedule(Loan loan){
        double i = monthlyRate(loan);
        int n = loan.getPlazoMeses();
        int gracia = loan.getPeriodoGraciaMeses() != null ? loan.getPeriodoGraciaMeses() : 0;
        double P = loan.getMonto();

        double cuota = (i == 0) ? (P / (n - gracia)) : (P * i) / (1 - Math.pow(1 + i, -(n - gracia)));

        List<PaymentScheduleItem> items = new ArrayList<>();
        double saldo = P;
        LocalDate fecha = loan.getFechaInicio() != null ? loan.getFechaInicio() : LocalDate.now();

        // Periodo de gracia total: se paga solo interés (o 0 si se define gracia total sin pagos)
        for (int k = 1; k <= n; k++){
            PaymentScheduleItem item = new PaymentScheduleItem();
            item.setLoan(loan);
            item.setNumeroCuota(k);
            item.setFechaPago(fecha.plusMonths(k));

            double interes = saldo * i;
            double amort = 0.0;
            double pago = 0.0;

            if (k <= gracia){
                // gracia total: no amortiza; puedes alternar pago=interes si norma exige
                pago = 0.0;
                amort = 0.0;
                saldo = saldo + 0.0; // saldo se mantiene
            } else {
                pago = cuota;
                amort = pago - interes;
                saldo = Math.max(0.0, saldo - amort);
            }

            item.setCuota(round2(pago));
            item.setInteres(round2(interes));
            item.setAmortizacion(round2(amort));
            item.setSaldo(round2(saldo));
            items.add(item);
        }
        // Ajuste final por redondeos
        if (!items.isEmpty()) {
            PaymentScheduleItem last = items.get(items.size()-1);
            last.setSaldo(0.0);
        }
        return items;
    }

    public double calculateVAN(double tasaDescuentoMensual, List<Double> cashflows){
        double van = 0.0;
        for (int t = 0; t < cashflows.size(); t++){
            van += cashflows.get(t) / Math.pow(1 + tasaDescuentoMensual, t);
        }
        return round2(van);
    }

    public double calculateTIR(List<Double> cashflows){
        UnivariateFunction f = r -> {
            double npv = 0.0;
            for (int t = 0; t < cashflows.size(); t++){
                npv += cashflows.get(t) / Math.pow(1 + r, t);
            }
            return npv;
        };
        BrentSolver solver = new BrentSolver(1e-12, 1e-8);
        try {
            double tir = solver.solve(1000, f, -0.9, 1.0); // busca raíz entre -90% y 100% mensual
            return round4(tir);
        } catch (Exception e){
            return Double.NaN;
        }
    }

    public List<Double> loanCashflows(Loan loan, List<PaymentScheduleItem> items){
        List<Double> cf = new ArrayList<>();
        // t=0: desembolso (negativo para el cliente)
        cf.add(-loan.getMonto());
        for (PaymentScheduleItem item : items){
            cf.add(item.getCuota());
        }
        return cf;
    }

    private double monthlyRate(Loan loan){
        if ("NOMINAL".equalsIgnoreCase(loan.getTipoTasa())){
            int m = loan.getCapitalizacionPorAnio() != null ? loan.getCapitalizacionPorAnio() : 12;
            return RateUtils.nominalToMonthlyPeriodic(loan.getTasaAnual(), m);
        } else {
            return RateUtils.effectiveAnnualToMonthly(loan.getTasaAnual());
        }
    }

    private double round2(double x){ return Math.round(x * 100.0) / 100.0; }
    private double round4(double x){ return Math.round(x * 10000.0) / 10000.0; }
}
