package com.fed.mivivienda.controller;

import com.fed.mivivienda.dto.LoanRequest;
import com.fed.mivivienda.dto.PaymentScheduleResponse;
import com.fed.mivivienda.entity.*;
import com.fed.mivivienda.repository.OperationRepository;
import com.fed.mivivienda.repository.LoanRepository;
import com.fed.mivivienda.repository.FinancialIndicatorRepository;
import com.fed.mivivienda.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final LoanService loanService;
    private final OperationRepository operationRepo;
    private final LoanRepository loanRepo;
    private final FinancialIndicatorRepository indicatorRepo;

    public LoanController(LoanService loanService,
                          OperationRepository operationRepo,
                          LoanRepository loanRepo,
                          FinancialIndicatorRepository indicatorRepo) {
        this.loanService = loanService;
        this.operationRepo = operationRepo;
        this.loanRepo = loanRepo;
        this.indicatorRepo = indicatorRepo;
    }

    // 🔹 Crear préstamo
    @PostMapping
    public ResponseEntity<Loan> create(@RequestBody LoanRequest req){
        Operation op = operationRepo.findById(req.getOperationId()).orElseThrow();
        Loan loan = new Loan();
        loan.setOperation(op);
        loan.setMonto(req.getMonto());
        loan.setMoneda(req.getMoneda());
        loan.setTasaAnual(req.getTasaAnual());
        loan.setTipoTasa(req.getTipoTasa());
        loan.setCapitalizacionPorAnio(req.getCapitalizacionPorAnio());
        loan.setPlazoMeses(req.getPlazoMeses());
        loan.setPeriodoGraciaMeses(req.getPeriodoGraciaMeses());
        loan.setFechaInicio(req.getFechaInicio());

        Loan saved = loanService.create(loan);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Listar todos los préstamos
    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = loanRepo.findAll();
        return ResponseEntity.ok(loans);
    }

    // 🔹 Cronograma de pagos
    @GetMapping("/{loanId}/schedule")
    public ResponseEntity<PaymentScheduleResponse> schedule(@PathVariable Long loanId){
        List<PaymentScheduleItem> items = loanService.schedule(loanId);
        return ResponseEntity.ok(new PaymentScheduleResponse(loanId, items));
    }

    // 🔹 Indicadores financieros
    @GetMapping("/{loanId}/indicators")
    public ResponseEntity<?> indicators(@PathVariable Long loanId){
        FinancialIndicator ind = indicatorRepo.findAll().stream()
                .filter(i -> i.getLoan().getId().equals(loanId)).findFirst()
                .orElse(null);
        if (ind == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(ind);
    }
}
