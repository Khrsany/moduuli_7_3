package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "resultAmount", nullable = false)
    private double resultAmount;

    // Lähdevaluutta (EUR, USD, SEK, jne.)
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "source_currency_code", referencedColumnName = "code")
    private Currency sourceCurrency;

    // Kohdevaluutta
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "target_currency_code", referencedColumnName = "code")
    private Currency targetCurrency;

    // Tyhjä konstruktori vaaditaan JPA:lle
    public Transaction() {}

    public Transaction(double amount, double resultAmount, Currency sourceCurrency, Currency targetCurrency) {
        this.amount = amount;
        this.resultAmount = resultAmount;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
    }

    // Getterit ja setterit
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getResultAmount() {
        return resultAmount;
    }

    public void setResultAmount(double resultAmount) {
        this.resultAmount = resultAmount;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", resultAmount=" + resultAmount +
                ", sourceCurrency=" + (sourceCurrency != null ? sourceCurrency.getCode() : "null") +
                ", targetCurrency=" + (targetCurrency != null ? targetCurrency.getCode() : "null") +
                '}';
    }
}
