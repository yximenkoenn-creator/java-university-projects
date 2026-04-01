package cv8bpog;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

public class CV8BPOG extends Application {

    private Pane drawingPane;
    private ColorPicker colorPicker;
    private Slider sizeSlider;
    private ComboBox<String> shapeComboBox;
    private Label coordinatesLabel;

    private double pressX, pressY;
    private Shape previewShape;
    private boolean leftDragStarted = false;

    private double dragStartSceneX, dragStartSceneY;
    private double initialTranslateX, initialTranslateY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        drawingPane = new Pane();
        drawingPane.setPrefSize(900, 520);
        drawingPane.setStyle("-fx-background-color: #eeeeee;");

        Button exitButton = new Button("Exit");

        colorPicker = new ColorPicker(Color.LIMEGREEN);

        sizeSlider = new Slider(10, 100, 40);
        sizeSlider.setShowTickLabels(true);
        sizeSlider.setShowTickMarks(true);
        sizeSlider.setMajorTickUnit(10);

        shapeComboBox = new ComboBox<>();
        shapeComboBox.getItems().addAll("ELLIPSE", "RECTANGLE", "HEXAGON");
        shapeComboBox.setValue("ELLIPSE");

        coordinatesLabel = new Label("x: 0  y: 0");

        HBox controlPanel = new HBox(15,
                exitButton,
                new Label("Color:"), colorPicker,
                new Label("Size:"), sizeSlider,
                new Label("Shape:"), shapeComboBox,
                coordinatesLabel
        );
        controlPanel.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setCenter(drawingPane);
        root.setBottom(controlPanel);

        drawingPane.setOnMouseMoved(e
                -> coordinatesLabel.setText("x: " + (int) e.getX() + "  y: " + (int) e.getY()));

        drawingPane.setOnMouseDragged(e
                -> coordinatesLabel.setText("x: " + (int) e.getX() + "  y: " + (int) e.getY()));

        drawingPane.setOnMousePressed(event -> {

            if (event.getButton() == MouseButton.PRIMARY) {
                pressX = event.getX();
                pressY = event.getY();
                leftDragStarted = true;

                drawingPane.getChildren().remove(previewShape);

                previewShape = createShapeFromCenter(
                        shapeComboBox.getValue(),
                        pressX,
                        pressY,
                        sizeSlider.getValue(),
                        colorPicker.getValue()
                );

                previewShape.setOpacity(0.5);
                previewShape.setMouseTransparent(true);
                drawingPane.getChildren().add(previewShape);
            }

            if (event.getButton() == MouseButton.SECONDARY) {
                Shape nearest = findNearestShape(event.getX(), event.getY());
                if (nearest != null) {
                    drawingPane.getChildren().remove(nearest);
                }
            }
        });

        drawingPane.setOnMouseDragged(event -> {
            if (leftDragStarted && event.getButton() == MouseButton.PRIMARY) {

                drawingPane.getChildren().remove(previewShape);

                double w = Math.abs(event.getX() - pressX);
                double h = Math.abs(event.getY() - pressY);

                if (w < 5 && h < 5) {
                    previewShape = createShapeFromCenter(
                            shapeComboBox.getValue(),
                            pressX,
                            pressY,
                            sizeSlider.getValue(),
                            colorPicker.getValue()
                    );
                } else {
                    previewShape = createShapeFromBounds(
                            shapeComboBox.getValue(),
                            pressX,
                            pressY,
                            event.getX(),
                            event.getY(),
                            colorPicker.getValue()
                    );
                }

                previewShape.setOpacity(0.5);
                previewShape.setMouseTransparent(true);
                drawingPane.getChildren().add(previewShape);
            }
        });

        
        drawingPane.setOnMouseReleased(event -> {
            if (event.getButton() == MouseButton.PRIMARY && leftDragStarted) {
                leftDragStarted = false;

                drawingPane.getChildren().remove(previewShape);

                double w = Math.abs(event.getX() - pressX);
                double h = Math.abs(event.getY() - pressY);

                Shape shape;

                if (w < 5 && h < 5) {
                    shape = createShapeFromCenter(
                            shapeComboBox.getValue(),
                            pressX,
                            pressY,
                            sizeSlider.getValue(),
                            colorPicker.getValue()
                    );
                } else {
                    shape = createShapeFromBounds(
                            shapeComboBox.getValue(),
                            pressX,
                            pressY,
                            event.getX(),
                            event.getY(),
                            colorPicker.getValue()
                    );
                }

                addShapeHandlers(shape);
                drawingPane.getChildren().add(shape);
            }
        });

        exitButton.setOnAction(e -> stage.close());

        Scene scene = new Scene(root, 980, 680);
        stage.setTitle("Shapes Drawing");
        stage.setScene(scene);
        stage.show();
    }

    
    private Shape createShapeFromCenter(String type, double x, double y, double size, Color color) {
        switch (type) {
            case "RECTANGLE":
                Rectangle r = new Rectangle(x - size / 2, y - size / 2, size, size);
                r.setFill(color);
                return r;

            case "HEXAGON":
                Polygon hex = createHexagon(x, y, size / 2);
                hex.setFill(color);
                return hex;

            default:
                Ellipse e = new Ellipse(x, y, size / 2, size / 3);
                e.setFill(color);
                return e;
        }
    }

    
    private Shape createShapeFromBounds(String type, double x1, double y1, double x2, double y2, Color color) {
        double left = Math.min(x1, x2);
        double top = Math.min(y1, y2);
        double w = Math.abs(x2 - x1);
        double h = Math.abs(y2 - y1);

        double cx = left + w / 2;
        double cy = top + h / 2;

        switch (type) {
            case "RECTANGLE":
                Rectangle r = new Rectangle(left, top, w, h);
                r.setFill(color);
                return r;

            case "HEXAGON":
                Polygon hex = createHexagon(cx, cy, Math.min(w, h) / 2);
                hex.setFill(color);
                return hex;

            default:
                Ellipse e = new Ellipse(cx, cy, w / 2, h / 2);
                e.setFill(color);
                return e;
        }
    }

    private Polygon createHexagon(double cx, double cy, double r) {
        Polygon p = new Polygon();
        for (int i = 0; i < 6; i++) {
            double angle = Math.toRadians(60 * i);
            p.getPoints().addAll(
                    cx + r * Math.cos(angle),
                    cy + r * Math.sin(angle)
            );
        }
        return p;
    }

    private void addShapeHandlers(Shape shape) {

        
        shape.setOnMousePressed(e -> {
            if (e.getButton() == MouseButton.SECONDARY) {
                drawingPane.getChildren().remove(shape);
                e.consume();
            }

            if (e.getButton() == MouseButton.MIDDLE) {
                dragStartSceneX = e.getSceneX();
                dragStartSceneY = e.getSceneY();
                initialTranslateX = shape.getTranslateX();
                initialTranslateY = shape.getTranslateY();
            }
        });

        
        shape.setOnMouseDragged(e -> {
            if (e.getButton() == MouseButton.MIDDLE) {
                shape.setTranslateX(initialTranslateX + (e.getSceneX() - dragStartSceneX));
                shape.setTranslateY(initialTranslateY + (e.getSceneY() - dragStartSceneY));
            }
        });
    }

    private Shape findNearestShape(double x, double y) {
        Shape nearest = null;
        double min = Double.MAX_VALUE;

        for (var node : drawingPane.getChildren()) {
            if (!(node instanceof Shape)) {
                continue;
            }

            Shape s = (Shape) node;
            if (s == previewShape) {
                continue;
            }

            double cx = s.getBoundsInParent().getMinX() + s.getBoundsInParent().getWidth() / 2;
            double cy = s.getBoundsInParent().getMinY() + s.getBoundsInParent().getHeight() / 2;

            double dist = Math.pow(x - cx, 2) + Math.pow(y - cy, 2);

            if (dist < min) {
                min = dist;
                nearest = s;
            }
        }

        return nearest;
    }
}
