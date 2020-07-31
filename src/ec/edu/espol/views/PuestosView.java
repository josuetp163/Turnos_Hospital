package ec.edu.espol.views;

import ec.edu.espol.common.Puesto;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class PuestosView implements View{
    private BorderPane root;
    public static Label lblHora;
    private static Label lblPuesto;
    private Button btnAsignarEditarPuesto;
    private Button btnDesasignarPuesto;
    private Button btnEliminarPuesto;
    private Button btnNuevoPuesto;
    private Button btnCancelar;

    public PuestosView() {
        crearEstructura();
    }

    private void crearEstructura(){
        root = new BorderPane();
        crearBotones();
        crearLabels();
        GridPane center = new GridPane();
        center.setHgap(10);
        center.setVgap(10);
        center.setPadding(new Insets(25, 25, 25, 25));
        center.add(lblPuesto,0,0);
        center.add(btnNuevoPuesto,0,1);
        center.add(btnAsignarEditarPuesto,1,1);
        center.add(btnDesasignarPuesto,2,1);
        center.add(btnEliminarPuesto,3,1);
        lblHora.setAlignment(Pos.TOP_RIGHT);
        center.setAlignment(Pos.CENTER);
        btnCancelar.setAlignment(Pos.CENTER_LEFT);
        root.setTop(lblHora);
        root.setCenter(center);
        root.setBottom(btnCancelar);

    }

    private void crearBotones(){
        btnAsignarEditarPuesto = new Button("ASIGNAR/EDITAR");
        btnAsignarEditarPuesto.setOnAction(e->{
            MainScene.asignarEditarPuestoView.actualizarCombos();
            MainScene.scene.setRoot(MainScene.asignarEditarPuestoView.getRoot());
            MainScene.allowDrag();
        });
        btnNuevoPuesto = new Button("NUEVO");
        btnNuevoPuesto.setOnAction(e->{
            Platform.runLater(()->{
                MainScene.nuevoPuestoView.actualizarCombos();
                MainScene.nuevoPuestoView.getTxtPuesto().setPromptText(String.valueOf(Puesto.getPuestosTotales()+1));
                MainScene.allowDrag();
            });
            MainScene.scene.setRoot(MainScene.nuevoPuestoView.getRoot());
        });
        btnDesasignarPuesto = new Button("DESASIGNAR");
        btnDesasignarPuesto.setOnAction(e->{
            MainScene.desasignarPuestoView.actualizarCombos();
            MainScene.scene.setRoot(MainScene.desasignarPuestoView.getRoot());
            MainScene.allowDrag();
        });
        btnEliminarPuesto = new Button("ELIMINAR");
        btnEliminarPuesto.setOnAction(e->{
            MainScene.eliminarPuestoView.actualizarCombos();
            MainScene.scene.setRoot(MainScene.eliminarPuestoView.getRoot());
            MainScene.allowDrag();
        });

        btnCancelar = new Button("CANCELAR");
        btnCancelar.setOnAction(e->{
            MainScene.scene.setRoot(MainScene.inicio.getRoot());
            MainScene.allowDrag();
        });
    }
    private static void crearLabels(){
        lblHora = new Label("");
        lblHora.setId("lblHora");
        lblPuesto = new Label("Puesto");
    }

    public BorderPane getRoot(){
        return root;
    }
}

