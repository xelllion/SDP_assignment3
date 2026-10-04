public class Main {

    public static void main(String[] args) {
        if (args.length == 1 && "--demo".equals(args[0])) {
            runDemo();
            return;
        }

        System.out.println("Run with: java -cp out Main --demo");
    }

    private static void runDemo() {
        int passed = 0;
        int total = 5;

        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();

        Circle circleVector = new Circle("C1", vector, 2);
        Circle circleRaster = new Circle("C2", raster, 2);
        Square squareVector = new Square("S1", vector, 3);
        Square squareRaster = new Square("S2", raster, 3);

        passed += check(
                "T1",
                "Circle + VectorRenderer",
                circleVector.execute(),
                "VECTOR circle radius=2"
        );

        passed += check(
                "T2",
                "Circle + RasterRenderer",
                circleRaster.execute(),
                "RASTER circle radius=2"
        );

        passed += check(
                "T3",
                "Square + VectorRenderer",
                squareVector.execute(),
                "VECTOR square side=3"
        );

        passed += check(
                "T4",
                "Square + RasterRenderer",
                squareRaster.execute(),
                "RASTER square side=3"
        );

        passed += checkRuntimeSwitch();

        System.out.println("SUMMARY: " + passed + "/" + total + " PASS");
    }

    private static int check(
            String testId,
            String combination,
            String actual,
            String expected) {

        boolean passed = actual.equals(expected);

        System.out.println(
                testId + " " +
                        status(passed) +
                        " | " + combination +
                        " | result=" + actual
        );

        if (!passed) {
            System.out.println("  expected=" + expected);
        }

        return passed ? 1 : 0;
    }

    private static int checkRuntimeSwitch() {
        Circle circle = new Circle(
                "C-SWITCH",
                new VectorRenderer(),
                2
        );

        Circle originalReference = circle;

        String originalId = circle.getId();
        int originalRadius = circle.getRadius();

        String before = circle.execute();

        circle.setImplementation(new RasterRenderer());

        Circle afterSwitchReference = circle;
        String after = circle.execute();

        boolean sameObject =
                originalReference == afterSwitchReference;

        boolean stateUnchanged =
                originalId.equals(circle.getId())
                        && originalRadius == circle.getRadius();

        boolean resultCorrect =
                before.equals("VECTOR circle radius=2")
                        && after.equals("RASTER circle radius=2");

        boolean passed =
                sameObject
                        && stateUnchanged
                        && resultCorrect;

        System.out.println(
                "T5 " + status(passed)
                        + " | sameObject=" + sameObject
                        + " | stateUnchanged=" + stateUnchanged
        );

        System.out.println(
                "  before=" + before
                        + " | after=" + after
        );

        if (!passed) {
            System.out.println(
                    "  expected same object, unchanged ID/radius, "
                            + "and Vector -> Raster results"
            );
        }

        return passed ? 1 : 0;
    }

    private static String status(boolean passed) {
        return passed ? "PASS" : "FAIL";
    }
}