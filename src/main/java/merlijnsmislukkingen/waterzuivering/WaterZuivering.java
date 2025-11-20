/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package merlijnsmislukkingen.waterzuivering;

import java.util.HashMap;
import java.util.Map;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author merlijn
 */
public class WaterZuivering extends Application {

    final int AANTAL = 5;
    int water = 1;
    double stikstof = 50;
    double fosfor = 10;
    double bacterie = 0.00005;
    double algen = 3;
    double medicijnresten = 0.01;
    double microplastics = 0.05;
    double metalen = 0.175;
    double pH = 7.25;
    double[] bacterieRij = new double[AANTAL];
    double[] fosforRij = new double[AANTAL];
    double[] stikstofRij = new double[AANTAL];
    double[] algenRij = new double[AANTAL];
    double[] medicijnrestenRij = new double[AANTAL];
    double[] microplasticsRij = new double[AANTAL];
    double[] metalenRij = new double[AANTAL];
    String[] zuiveringsMethode = new String[AANTAL];
    Map<String, Double> concentratieStoffen = new HashMap<>();
    Map<String, Double> voorkomen = new HashMap<>();
    private double BACTERIE_FILTRATIE = 30;
    Button recalc = new Button("Herbereken");
    TextField bacterieFilterInput;

    @Override
    public void start(Stage stage) throws Exception {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        LineChart lineChart = new LineChart(xAxis, yAxis);
        lineChart.setData(getChartData());
        lineChart.setTitle("zuivering");
        StackPane root = new StackPane();
//        HBox hbox = new HBox();
//        hbox.getChildren().add(lineChart);
        lineChart.setPrefWidth(1200);

        recalc.setOnAction(e -> {
            BACTERIE_FILTRATIE = Double.parseDouble(bacterieFilterInput.getText());
            calculate();
            lineChart.setData(getChartData());
        });
        VBox configBox = createConfigBox();
        SplitPane sp = new SplitPane(lineChart, configBox);
//        hbox.getChildren().add(configBox);
        root.getChildren().add(sp);
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }

    public VBox createConfigBox() {
        VBox configBox = new VBox();

        Label bacterieFilterLabel = new Label("bacterie filtratie");
        bacterieFilterInput = new TextField(Double.toString(BACTERIE_FILTRATIE));
        bacterieFilterInput.setPrefWidth(50);
        HBox bact = new HBox(bacterieFilterLabel, bacterieFilterInput);
        configBox.getChildren().addAll(bact, recalc);
        configBox.setPrefWidth(200);
        return configBox;
    }

    public ObservableList<XYChart.Series<String, Double>> getChartData() {
        ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
        Series<String, Double> bacterieSeries = new Series<>();
        Series<String, Double> fosforSeries = new Series<>();
        Series<String, Double> stikstofSeries = new Series<>();
        Series<String, Double> algenSeries = new Series<>();
        Series<String, Double> medicijnrestenSeries = new Series<>();
        Series<String, Double> microplasticsSeries = new Series<>();
        Series<String, Double> metalenSeries = new Series<>();
        
        for (int i = 0; i < AANTAL; i++) {
            bacterieSeries.getData().add(new XYChart.Data(zuiveringsMethode[i], bacterieRij[i]));
            fosforSeries.getData().add(new XYChart.Data(zuiveringsMethode[i], fosforRij[i]));
            stikstofSeries.getData().add(new XYChart.Data(zuiveringsMethode[i], stikstofRij[i]));
            algenSeries.getData().add(new XYChart.Data(zuiveringsMethode[i], algenRij[i]));
            medicijnrestenSeries.getData().add(new XYChart.Data(zuiveringsMethode[i], medicijnrestenRij[i]));
            microplasticsSeries.getData().add(new XYChart.Data(zuiveringsMethode[i], microplasticsRij[i]));
            metalenSeries.getData().add(new XYChart.Data(zuiveringsMethode[i], metalenRij[i]));
                        
        }
        bacterieSeries.setName("Bacterie");
        fosforSeries.setName("Fosfor");
        stikstofSeries.setName("Stikstof");
        algenSeries.setName("Algen");
        medicijnrestenSeries.setName("Medicijnresten");
        microplasticsSeries.setName("Microplastics");
        metalenSeries.setName("Metalen");
        answer.add(bacterieSeries);
        answer.add(fosforSeries);
        answer.add(stikstofSeries);
        answer.add(algenSeries);
        answer.add(medicijnrestenSeries);
        answer.add(microplasticsSeries);
        answer.add(metalenSeries);
        return answer;
    }

    public static void main(String[] args) {
        WaterZuivering w = new WaterZuivering();
        launch(args);

    }

    public WaterZuivering() {
        calculate();
    }

    public void calculate() {

//        concentratieStoffen.put("stikstof", stikstof);
//        concentratieStoffen.put("fosfor", fosfor);
//        concentratieStoffen.put("bacterien", bacterie);
//        concentratieStoffen.put("algen", algen);
//        concentratieStoffen.put("medicijnresten", medicijnresten);
//        concentratieStoffen.put("microplastics", microplastics);
//        concentratieStoffen.put("metalen", metalen);
//        concentratieStoffen.put("pH-waarde", pH);
        System.err.println("VERSCHIL / VERSCHIL2 /UI?");
        bacterieRij[0] = 100;
        fosforRij[0] = 100;
        stikstofRij[0] = 100;
        algenRij[0] = 100;
        medicijnrestenRij[0] = 100;
        microplasticsRij[0] = 100;
        metalenRij[0] = 100;
        zuiveringsMethode[0] = "Begin";
        voorkomen.put("bacterien", 1e9);          // 1 × 10^9
        voorkomen.put("stikstof", 2.15e19);       // 2.15 × 10^19
        voorkomen.put("fosfor", 1.94e19);         // 1.94 × 10^19
        voorkomen.put("algen", 1e8);              // 1 × 10^8 (voorbeeld)
        voorkomen.put("medicijnresten", 4e18);    // 4 × 10^18
        voorkomen.put("microplastics", 1.91e6);   // 1.91 × 10^6
        voorkomen.put("metalen", 1.08e19);

        System.err.println("beginwaarde:");
        updateMap();
        printMap();
        System.err.println("Absoluut voorkomen");
        printHvlheid();
        System.err.println("");
        System.err.println("");
        System.err.println("eerste zuivering");

//        filtratie(1);
//        coagulatieFlocculatie(1);
        neutralisatie(1);
//        microOrganisme(1);

        updateMap();
        printMap();
        System.err.println("absoluut voorkomen");
        printHvlheid();
        System.err.println("______________________________");
        System.err.println("");
        System.err.println("tweede zuivering");

        filtratie(2);
//        coagulatieFlocculatie(2);
//        neutralisatie(2);
//        microOrganisme(2);

        updateMap();
        printMap();
        System.err.println("_________________________________");
        System.err.println("");
        System.err.println("derde zuivering");

//        filtratie(3);
        coagulatieFlocculatie(3);
//        neutralisatie(3);
//        microOrganisme(3);

        updateMap();
        printMap();
        System.err.println("_______________________________");
        System.err.println("");
        System.err.println("vierde zuivering");
       
//        filtratie(4);
//        coagulatieFlocculatie(4);
//        neutralisatie(4);
        microOrganisme(4);

        updateMap();
        printMap();
        System.err.println("absoluut voorkomen");
        printHvlheid();
        printBacterie();
    }

    public void filtratie(int step) {
        zuiveringsMethode [step] = "Filtratie";
        stikstof = stikstof - 5 * stikstof / 100;
        stikstofRij[step] = stikstofRij[step-1] - 5. *stikstofRij[step-1]/100.;
        fosfor = fosfor - 10 * fosfor / 100;
        fosforRij[step] = fosforRij[step-1] - 10. *fosforRij[step-1]/100.;
        bacterie = bacterie - BACTERIE_FILTRATIE * bacterie / 100;
//        System.err.println("  ");
//        System.err.println("");
//        System.err.println("");
//        System.err.println("");
//        System.err.println("bacterierij[0] = "+bacterieRij[0]);
        bacterieRij[step] = bacterieRij[step - 1] - BACTERIE_FILTRATIE * bacterieRij[step-1] / 100;
        algen = algen - 50 * algen / 100;
        algenRij[step] = algenRij[step-1] - 50. *algenRij[step-1]/100.;
        medicijnresten = medicijnresten - 10 * medicijnresten / 100;
        medicijnrestenRij[step] = medicijnrestenRij[step-1] - 10. *medicijnrestenRij[step-1]/100.;
        microplastics = microplastics - 30 * microplastics / 100;
        microplasticsRij[step] = microplasticsRij[step-1] - 30. *microplasticsRij[step-1]/100.;
        metalen = metalen - 10 * metalen / 100;
        metalenRij[step] = metalenRij[step-1] - 10. *metalenRij[step-1]/100.;
    }

    public void coagulatieFlocculatie(int step) {
        zuiveringsMethode [step] = "Coagulatie/flocculatie";
        stikstof = stikstof - 15 * stikstof / 100;
        stikstofRij[step] = stikstofRij[step-1] - 15. *stikstofRij[step-1]/100.;
        fosfor = fosfor - 70 * fosfor / 100;
        fosforRij[step] = fosforRij[step-1] - 70. *fosforRij[step-1]/100.;
        bacterie = bacterie * 0.1;
        bacterieRij[step] = bacterieRij[step - 1] * .1;
        algen = algen - 90 * algen / 100;
        algenRij[step] = algenRij[step-1] - 90. *algenRij[step-1]/100.;
        medicijnresten = medicijnresten - 20 * medicijnresten / 100;
        medicijnrestenRij[step] = medicijnrestenRij[step-1] - 20. *medicijnrestenRij[step-1]/100.;
        microplastics = microplastics - 80 * microplastics / 100;
        microplasticsRij[step] = microplasticsRij[step-1] - 80. *microplasticsRij[step-1]/100.;
        metalen = metalen - 65 * metalen / 100;
        metalenRij[step] = metalenRij[step-1] - 65. *metalenRij[step-1]/100.;
    }

    public void neutralisatie(int step) {
        zuiveringsMethode [step] = "Neutralisatie";
        pH = 7;
        stikstofRij[step] = stikstofRij[step-1] - 0. *stikstofRij[step-1]/100.;
        fosforRij[step] = fosforRij[step-1] - 0. *fosforRij[step-1]/100.;
        bacterieRij[step] = bacterieRij[step - 1] * 1;
        algenRij[step] = algenRij[step-1] - 0. *algenRij[step-1]/100.;
        medicijnrestenRij[step] = medicijnrestenRij[step-1] - 0. *medicijnrestenRij[step-1]/100.;
        microplasticsRij[step] = microplasticsRij[step-1] - 0. *microplasticsRij[step-1]/100.;
        metalenRij[step] = metalenRij[step-1] - 0. *metalenRij[step-1]/100.;
    }

    public void microOrganisme(int step) {
        zuiveringsMethode [step] = "microOrganismes";
        stikstof = stikstof - 50 * stikstof / 100;
        stikstofRij[step] = stikstofRij[step-1] - 50. *stikstofRij[step-1]/100.;
        fosfor = fosfor - 40 * fosfor / 100;
        fosforRij[step] = fosforRij[step-1] - 40. *fosforRij[step-1]/100.;
        algen = algen - 30 * algen / 100;
        algenRij[step] = algenRij[step-1] - 30. *algenRij[step-1]/100.;
        medicijnresten = medicijnresten - 15 * medicijnresten / 100;
        medicijnrestenRij[step] = medicijnrestenRij[step-1] - 15. *medicijnrestenRij[step-1]/100.;
        metalen = metalen - 5 * metalen / 100;
        bacterieRij[step] = bacterieRij[step - 1] * 1;
        microplasticsRij[step] = microplasticsRij[step-1] - 0. *microplasticsRij[step-1]/100.;
        metalenRij[step] = metalenRij[step-1] - 5. *metalenRij[step-1]/100.;
    }

    public void verdamping(int step) {
        System.err.println("achterwege gelaten voor het moment. 99% effectiviteit voor merendeel van de stoffen.");
    }

    public void updateMap() {
        concentratieStoffen.put("stikstof", stikstof);
        concentratieStoffen.put("fosfor", fosfor);
        concentratieStoffen.put("bacterien", bacterie);
        concentratieStoffen.put("algen", algen);
        concentratieStoffen.put("medicijnresten", medicijnresten);
        concentratieStoffen.put("microplastics", microplastics);
        concentratieStoffen.put("metalen", metalen);
        concentratieStoffen.put("pH-waarde", pH);
    }

    public void printMap() {
        for (Map.Entry<String, Double> entry : concentratieStoffen.entrySet()) {
            String key = entry.getKey();
            double value = entry.getValue();
            System.out.printf("%s -> %.2e%n", entry.getKey(), entry.getValue());
        }
    }

    public void printHvlheid() {
        for (Map.Entry<String, Double> entry : concentratieStoffen.entrySet()) {
            String key = entry.getKey();
            double concentratie = entry.getValue();
            double hoeveelheid = voorkomen.getOrDefault(key, 0.0); // voorkomt NullPointerException
            String type = entry.getKey();
            if ((type != "bacterien") && (type != "algen")) {
                type = "moleculen";
            }
            double resultaat = concentratie * hoeveelheid;
            System.err.printf("%-15s -> %.3e %s%n", key, resultaat, type);

        }
    }

    void printBacterie() {
        for (int i = 0; i < AANTAL; i++) {
            System.err.println("Bacterie[" + i + "] = " + bacterieRij[i]+ " |   Fosfor["+ i + "] = " + fosforRij[i]+ " |     Stikstof["+ i + "] = " + stikstofRij[i] + " |       Algen["+ i + "] = " + algenRij[i]  + " |      Medicijnresten["+ i + "] = " + medicijnrestenRij[i]+ " |      Microplastics["+ i + "] = " + microplasticsRij[i]+ " |      Metalen["+ i + "] = " + metalenRij[i]);
        }
    }

}
