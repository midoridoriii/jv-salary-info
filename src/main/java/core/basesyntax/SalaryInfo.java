package core.basesyntax;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SalaryInfo {
    public String getSalaryInfo(String[] names, String[] data, String dateFrom, String dateTo) {
        final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        final int dateIndex = 0;
        final int nameIndex = 1;
        final int hoursIndex = 2;
        final int rateIndex = 3;
        LocalDate from = LocalDate.parse(dateFrom, dateFormatter);
        LocalDate to = LocalDate.parse(dateTo, dateFormatter);

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
                LocalDate recDate = LocalDate.parse(parts[dateIndex], dateFormatter);
                boolean inRange = !recDate.isBefore(from) && !recDate.isAfter(to);

                if (parts[nameIndex].equals(name) && inRange) {
                    int hours = Integer.parseInt(parts[hoursIndex]);
                    int rate = Integer.parseInt(parts[rateIndex]);
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
