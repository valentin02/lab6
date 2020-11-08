package sample;

import java.io.Serializable;
import java.util.Comparator;

public class Currency implements Serializable {

    private final static long serialVersionUID = -2958706369661932574L;
    public static Comparator<Currency> byNameAsc = Comparator.comparing(o -> o.getCurrency());
    public static Comparator<Currency> byNameDesc = (o1, o2) -> o2.getCurrency().compareTo(o1.getCurrency());
    public static Comparator<Currency> byIdAsc = (o1, o2) -> o1.getId() > o2.getId() ? 1 : o1.getId() < o2.getId() ? -1 : 0;
    public static Comparator<Currency> byIdDesc = (o1, o2) -> o1.getId() < o2.getId() ? 1 : o1.getId() > o2.getId() ? -1 : 0;
    private int id;
    private String pointDate;
    private String date;
    private double ask;
    private double bid;
    private double trendAsk;
    private double trendBid;
    private String currency;

    @Override
    public String toString() {
        return
                "\n" + currency.toUpperCase() +
                        " [id: " + id +
                        " ask: " + String.format("%2.2f", ask) +
                        " bid: " + String.format("%2.2f", bid) +
                        " trendAsk: " + String.format("%2.2f", trendAsk) +
                        " trendBid: " + String.format("%2.2f", trendBid) + "]";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPointDate() {
        return pointDate;
    }

    public void setPointDate(String pointDate) {
        this.pointDate = pointDate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getTrendAsk() {
        return trendAsk;
    }

    public void setTrendAsk(double trendAsk) {
        this.trendAsk = trendAsk;
    }

    public double getTrendBid() {
        return trendBid;
    }

    public void setTrendBid(double trendBid) {
        this.trendBid = trendBid;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

}