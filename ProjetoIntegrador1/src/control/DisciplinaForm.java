/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import model.Disciplina;

/**
 *
 * @author arthurcvm
 */
public class DisciplinaForm {
    @FXML
    private JFXTextField nomeField;
    @FXML
    private JFXTextField abreviacaoField;
    @FXML
    private JFXTextField chField;
    @FXML
    private JFXTextField semestreField;
    @FXML
    private JFXTextField professorField;
    
    private Disciplina disciplina;
    private Stage dialogStage;
    
    /**
     * Inicializa a classe controlle. Este método é chamado automaticamente
     * após o arquivo fxml ter sido carregado.
     */
    @FXML
    public void initialize() {        
        // Carregando lista de estados na Box.
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }
    
    @FXML
    private void cancelar(){
        disciplina.setNome(null);
        
        this.dialogStage.close(); //fecha a janela
    }
    
    @FXML
    private void cadastrar(){
        this.disciplina.setNome(nomeField.getText());
        this.disciplina.setAbreviacao(abreviacaoField.getText());
        this.disciplina.setCH(Integer.valueOf(chField.getText()));
//        this.disciplina.setProfessor(0);
//        this.disciplina.setSemestre(0);
        
        this.dialogStage.close(); //fecha a janela
    }
}
