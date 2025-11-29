package com.fed.mivivienda.util;

public class RateUtils {

    // Convierte tasa nominal anual con m capitalizaciones a tasa efectiva periódica mensual
    public static double nominalToMonthlyPeriodic(double nominalAnnual, int m){
        double effectiveAnnual = Math.pow(1 + nominalAnnual / m, m) - 1.0;
        return Math.pow(1 + effectiveAnnual, 1.0/12.0) - 1.0;
    }

    // Convierte tasa efectiva anual a tasa efectiva periódica mensual
    public static double effectiveAnnualToMonthly(double effectiveAnnual){
        return Math.pow(1 + effectiveAnnual, 1.0/12.0) - 1.0;
    }
}
