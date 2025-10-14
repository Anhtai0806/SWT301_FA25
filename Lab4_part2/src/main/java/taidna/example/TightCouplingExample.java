package taidna.example;

import java.util.logging.Level;
import java.util.logging.Logger;

// Printer class dùng Logger thay vì System.out
class Printer {
    private static final Logger logger = Logger.getLogger(Printer.class.getName());

    void print() {
        logger.log(Level.INFO, "Generating report...");
    }
}

// Report class dùng Dependency Injection
class Report {
    private final Printer printer;

    // Truyền Printer từ bên ngoài để giảm phụ thuộc
    Report(Printer printer) {
        this.printer = printer;
    }

    void generate() {
        printer.print();
    }

    public static void main(String[] args) {
        Printer printer = new Printer();
        Report report = new Report(printer);
        report.generate();
    }
}
