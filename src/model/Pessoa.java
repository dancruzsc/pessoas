package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
    Classe representando a entidade Pessoa.
*/

public class Pessoa {
    
    // Dados iniciais
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    
    // Dados de endereco
    /* Logradiuro, bairro, cidade e UF devem ser resgatados a partir da API
       pública http://viacep.com.br */
    
    private String cep;
    private String logradouro;
    private int numEndereco;
    private String complemento;
    private String bairro;
    private String cidade;
    private String UF;

    
    // Construtor vazio para motivos de teste
    public Pessoa() {
    }
    
    

    public Pessoa(String nome, String cpf, String telefone, 
            String endereco, String cep, int numEndereco, String complemento) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cep = cep;
        this.numEndereco = numEndereco;
        this.complemento = complemento;
        
        
    }
    
    public void getInfosAPI(String cep) {
        try {
            URL api = new URL("http://viacep.com.br/ws/" + cep + "/json");
            BufferedReader br = new BufferedReader(new InputStreamReader(api.openStream()));
            String teste;
            while((teste = br.readLine()) != null) {
                System.out.println(teste);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        
    }
}
