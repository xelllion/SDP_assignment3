public class Main {

    public static void main(String[] args) {
        Circle circle = new Circle(
                "circle-1",
                new VectorRenderer(),
                2
        );

        Square square = new Square(
                "square-1",
                new RasterRenderer(),
                3
        );

        System.out.println(circle.execute());
        System.out.println(square.execute());
    }
}