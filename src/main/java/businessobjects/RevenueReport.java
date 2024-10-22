package businessobjects;

import java.util.Date;

public class RevenueReport {
    String reportId;
    Date date;
    double revenue;

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public String getReportId() {
        return reportId;
    }

    public Date getDate() {
        return date;
    }

    public double getRevenue() {
        if(revenue == 0)
            throw new IllegalStateException("No revenue data available for the selected time period");

        return revenue;
    }

    public void DisplayReport()
    {
        System.out.println("ReportId: " + reportId);
        System.out.println("Date: " + date);
        System.out.println("Revenue: " + revenue);
    }
}
