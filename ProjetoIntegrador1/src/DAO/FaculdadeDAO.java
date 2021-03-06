/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConnectionFacotory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Faculdade;
import model.FaculdadeGenerica;

/**
 *
 * @author arthurcvm
 */
public class FaculdadeDAO {
    private Connection con;
    public FaculdadeDAO(){
        this.con = new ConnectionFacotory().getConnection();
    }
    public void insert(Faculdade faculdade){
        try {
            String insert = "INSERT INTO faculdade (nome, cnpj, rua, numero, bairro, convenio, cidade, estado)";
                    insert += " VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = this.con.prepareStatement(insert);
            stmt.setString(1, faculdade.getNome());
            stmt.setString(2, faculdade.getCNPJ());
            stmt.setString(3, faculdade.getRua());
            stmt.setInt(4, faculdade.getNumero());
            stmt.setString(5, faculdade.getBairro());
            stmt.setString(6, faculdade.getConvenio());
            stmt.setString(7, faculdade.getCidade());
            stmt.setString(8, faculdade.getEstado());
        
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FaculdadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id){
       try {
            String delete = "DELETE FROM faculdade WHERE ";
            delete+="idFACULDADE=?";
            PreparedStatement stmt = this.con.prepareStatement(delete);
            stmt.setInt(1, id);

            stmt.execute();
            stmt.close();
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(FaculdadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Faculdade> lista(){
        ArrayList<Faculdade> array = new ArrayList();
        try {
            String select = "SELECT * FROM faculdade";
            PreparedStatement stmt = this.con.prepareStatement(select);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Faculdade facul = new Faculdade();
                facul.setIdFaculdade(rs.getInt("idFACULDADE"));
                facul.setNome(rs.getString("nome"));
                facul.setCNPJ(rs.getString("cnpj"));
                facul.setRua(rs.getString("rua"));
                facul.setNumero(rs.getInt("numero"));
                facul.setBairro(rs.getString("bairro"));
                facul.setConvenio(rs.getString("convenio"));
                facul.setCidade(rs.getString("cidade"));
                facul.setEstado(rs.getString("estado"));
                array.add(facul);
            }
        } catch (SQLException ex) {
                Logger.getLogger(FaculdadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return array;
    }
    
    public ArrayList<FaculdadeGenerica> listaGen(){
        ArrayList<Faculdade> faculdadeList = this.lista();
        ArrayList<FaculdadeGenerica> faculdadeGenericaList = new ArrayList();
        for(Faculdade f: faculdadeList){
                faculdadeGenericaList.add(new FaculdadeGenerica(f));
            }
        
        return faculdadeGenericaList;
    }
    
    public void edit(Faculdade fac){
        try {
            String update = "UPDATE faculdade SET nome=?, cnpj=?, rua=?, numero=?, bairro=?,";
                update+="convenio=?, cidade=?, estado=? WHERE idFACULDADE=?";
            PreparedStatement stmt = this.con.prepareStatement(update);
            stmt.setString(1, fac.getNome());
            stmt.setString(2, fac.getCNPJ());
            stmt.setString(3, fac.getRua());
            stmt.setInt(4, fac.getNumero());
            stmt.setString(5, fac.getBairro());
            stmt.setString(6, fac.getConvenio());
            stmt.setString(7, fac.getCidade());
            stmt.setString(8, fac.getEstado());
            stmt.setInt(9, fac.getIdFaculdade());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(FaculdadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Faculdade> search(String dado){
        ArrayList<Faculdade> array = new ArrayList();
        try {
            String pesq = "SELECT * FROM "
                    + "faculdade WHERE nome LIKE '%?%'";
            PreparedStatement stmt = this.con.prepareStatement(pesq);
            stmt.setString(1, dado);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Faculdade facul= new Faculdade();
                facul.setIdFaculdade(rs.getInt("idFACULDADE"));
                facul.setNome(rs.getString("nome"));
                facul.setCNPJ(rs.getString("cnpj"));
                facul.setRua(rs.getString("rua"));
                facul.setNumero(rs.getInt("numero"));
                facul.setBairro(rs.getString("bairro"));
                facul.setConvenio(rs.getString("convenio"));
                facul.setCidade(rs.getString("cidade"));
                facul.setEstado(rs.getString("estado"));
                array.add(facul);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FaculdadeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }         
        return array;
    }
}
