package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate from = LocalDate.parse(dateFrom, fmt);
        LocalDate to = LocalDate.parse(dateTo, fmt);

        StringBuilder report = new StringBuilder();
        report.append("Report for period ")
                .append(dateFrom)
                .append(" - ")
                .append(dateTo)
                .append(System.lineSeparator());

        for (String name: names) {
            int salary = 0;

            for (String record: data) {
                String[] parts = record.split(" ");
                LocalDate recDate = LocalDate.parse(parts[0], fmt);
                boolean inRange = !recDate.isBefore(from) && !recDate.isAfter(to);

                if (parts[1].equals(name) && inRange) {
                    int hours = Integer.parseInt(parts[2]);
                    int rate = Integer.parseInt(parts[3]);
                    salary += hours * rate;
                }
            }
            report.append(name)
                    .append(" - ")
                    .append(salary)
                    .append(System.lineSeparator());
        }
        return report.toString().trim();
    }
}
