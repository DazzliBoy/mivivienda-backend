package com.fed.mivivienda.service;

import com.fed.mivivienda.entity.FinancialIndicator;
import com.fed.mivivienda.entity.Loan;
import com.fed.mivivienda.entity.PaymentScheduleItem;
import com.fed.mivivienda.repository.FinancialIndicatorRepository;
import com.fed.mivivienda.repository.LoanRepository;
import com.fed.mivivienda.repository.PaymentScheduleItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LoanService {
    private final LoanRepository loanRepo;
    private final PaymentScheduleItemRepository scheduleRepo;
    private final FinancialIndicatorRepository indicatorRepo;
    private final FinancialService financialService;

    public LoanService(LoanRepository loanRepo,
                       PaymentScheduleItemRepository scheduleRepo,
                       FinancialIndicatorRepository indicatorRepo,
                       FinancialService financialService) {
        this.loanRepo = loanRepo;
        this.scheduleRepo = scheduleRepo;
        this.indicatorRepo = indicatorRepo;
        this.financialService = financialService;
    }

    @Transactional
    public Loan create(Loan loan){
        Loan saved = loanRepo.save(loan);
        List<PaymentScheduleItem> items = financialService.generateSchedule(saved);
        scheduleRepo.saveAll(items);

        List<Double> cf = financialService.loanCashflows(saved, items);
        double tir = financialService.calculateTIR(cf);
        // Para VAN, usa tasa de descuento mensual (ej. tasa anual efectiva del préstamo):
        double tasaMensual = cf.size() > 1 ? (Math.pow(1 + loan.getTasaAnual(), 1.0/12.0) - 1.0) : 0.0;
        double van = financialService.calculateVAN(tasaMensual, cf);

        FinancialIndicator ind = new FinancialIndicator();
        ind.setLoan(saved);
        ind.setVan(van);
        ind.setTir(tir);
        indicatorRepo.save(ind);

        return saved;
    }

    public List<PaymentScheduleItem> schedule(Long loanId){
        return scheduleRepo.findByLoanIdOrderByNumeroCuotaAsc(loanId);
    }
}
