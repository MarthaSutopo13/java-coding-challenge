package com.martha.demo;

import org.apache.commons.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		Integer TK0 = 0, K0 = 1, K1 = 2, K2 = 3, K3 = 4;
		//No 2
		taxationScheme(25000000);

		//No 3
		taxRelief(K1, 6500000);
	}

	public static void taxationScheme(Integer monthlySalary) {
		Integer annualTaxIncome = monthlySalary * 12;

		System.out.println("========================================================");
		System.out.println("No1");
		System.out.println("Annual taxable income: "+ annualTaxIncome);
		System.out.printf("Annual Tax on this Income: %.0f\n", calculateTax(annualTaxIncome));
	}

	public static void taxRelief(Integer category, long monthlySalary) {
		long TK0 = 54000000, K0 = 58500000, K1 = 63000000, K2 = 67500000, K3 = 72000000;
		long annualTaxableIncome = 0;

		if (category == 0) {
			annualTaxableIncome = (monthlySalary * 12) - TK0;
		} else if (category == 1) {
			annualTaxableIncome = (monthlySalary * 12) - K0;
		} else if (category == 2) {
			annualTaxableIncome = (monthlySalary * 12) - K1;
		} else if (category == 3) {
			annualTaxableIncome = (monthlySalary * 12) - K2;
		} else if (category == 4) {
			annualTaxableIncome = (monthlySalary * 12) - K3;
		}

		System.out.println("========================================================");
		System.out.println("No2");
		System.out.println("Annual taxable income: "+ annualTaxableIncome);
		System.out.printf("Annual tax income: %.0f\n", calculateTax(annualTaxableIncome));
	}

	public static Double calculateTax(long annualTaxableIncome) {
		long[] maxIncome = {50000000, 250000000, 500000000};
		long[] decrease = {50000000, 200000000, 250000000};
		double[] rate = {0.05, 0.15, 0.25, 0.30};
		double annualTaxIncome = 0;


		if (annualTaxableIncome > 0 && annualTaxableIncome <= maxIncome[0]) {
			annualTaxIncome = rate[0] * annualTaxableIncome;
		} else {
			for (var a = 0; a < maxIncome.length; a++) {
				if (annualTaxableIncome >= maxIncome[a]) {
					annualTaxIncome += rate[a] * decrease[a];
					annualTaxableIncome -= decrease[a];
					if (a == maxIncome.length - 1) {
						annualTaxIncome += rate[a+1] * annualTaxableIncome;
					}
				} else {
					annualTaxIncome += rate[a] * annualTaxableIncome;
					annualTaxableIncome -= annualTaxableIncome;
				}
			}
		}

		return annualTaxIncome;
	}
}
