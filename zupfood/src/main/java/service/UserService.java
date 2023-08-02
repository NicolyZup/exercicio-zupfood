package service;

import connection.Conexao;
import models.Bebida;
import models.Lanche;

import java.sql.*;

public class UserService {
    static Connection connection = Conexao.getConnection();

    static Statement statement;

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void consultarLanches(){
        String sql = "SELECT * FROM lanches";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id")+") "
                         + resultSet.getString("nome")
                                + " - R$"+resultSet.getDouble("preco"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void consultarBebidas(){
        String sql = "SELECT * FROM bebidas";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id")+") "
                        + resultSet.getString("nome")
                        + " - R$"+resultSet.getDouble("preco"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void consultarCarrinho(){
        String sql = "SELECT * FROM pedidos";
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                System.out.println(resultSet.getInt("id")+" - "
                        + resultSet.getString("nome") +" - "
                        + resultSet.getInt("quantidade")
                        + " - R$"+resultSet.getDouble("valor_total"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private Lanche buscarLanchePorId(int id){
        Lanche lanche = null;
        String sql = "SELECT nome, preco FROM lanches WHERE id = "+id;
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                lanche = new Lanche();
                lanche.setId(id);
                lanche.setNome(resultSet.getString("nome"));
                lanche.setPreco(resultSet.getDouble("preco"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return lanche;
    }

    public void adicionarLanche(int id, int quantidade){
        Lanche lanche = buscarLanchePorId(id);
        if(lanche != null){
            double valorTotal = lanche.getPreco() * quantidade;
            String sql = "INSERT INTO pedidos (quantidade,nome, valor_unitario,valor_total) " +
                    "VALUES ("+quantidade+
                    ",'"+lanche.getNome()+"',"+lanche.getPreco()+","+valorTotal+")";
            try{
                statement.executeUpdate(sql);
            }catch(SQLException e){
                e.printStackTrace();
            }
            System.out.println("Pedido adicionado com sucesso!");
        }else{
            System.out.println("Item não localizado.");
        }
    }

    private Bebida buscarBebidaPorId(int id){
        Bebida bebida = null;
        String sql = "SELECT nome, preco FROM bebidas WHERE id = "+id;
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                bebida = new Bebida();
                bebida.setId(id);
                bebida.setNome(resultSet.getString("nome"));
                bebida.setPreco(resultSet.getDouble("preco"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return bebida;
    }

    public void adicionarBebida(int id, int quantidade){
        Bebida bebida = buscarBebidaPorId(id);
        if(bebida != null){
            double valorTotal = bebida.getPreco() * quantidade;
            String sql = "INSERT INTO pedidos (quantidade,nome, valor_unitario,valor_total) " +
                    "VALUES ("+quantidade+
                    ",'"+bebida.getNome()+"',"+bebida.getPreco()+","+valorTotal+")";
            try{
                statement.executeUpdate(sql);
            }catch(SQLException e){
                e.printStackTrace();
            }
            System.out.println("Pedido adicionado com sucesso!");
        }else{
            System.out.println("Item não localizado.");
        }
    }

    public double valorTotal(){
        double valorTotal = 0;
        String sql = "SELECT SUM(valor_total) AS valor_final FROM pedidos";
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                valorTotal = resultSet.getDouble("valor_final");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return valorTotal;
    }

    public void alterarPedido(int id, int quantidade){
        String sql =
    }
}
