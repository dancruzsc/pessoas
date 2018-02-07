package model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Classe representando a entidade Pessoa.
public class Pessoa {

    // Dados iniciais
    private String nome;
    private String cpf;
    private String telefone;

    // Dados de endereco
    /* Logradouro, bairro, cidade e UF devem ser resgatados a partir da API
       pública http://viacep.com.br */
    private String cep;
    private String logradouro;
    private String numEndereco;
    private String complemento;
    private String bairro;
    private String cidade;
    private String UF;

    // Construtor padrão da classe.
    public Pessoa(String nome, String cpf, String telefone,
            String cep, String numEndereco, String complemento) throws Exception {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.cep = cep;
        this.numEndereco = numEndereco;
        this.complemento = complemento;
        preencheEndereco(parseJSON(getInfosAPI(cep)));
    }

    // Preenche os dados restantes do endereco. 
    // TODO: incluir tratamento em caso de erro
    private void preencheEndereco(HashMap<String, String> enderecos) {
        if (enderecos.get("erro") == null) {
            return;
        }

        this.logradouro = enderecos.get("logradouro") + " "
                + enderecos.get("complemento");
        this.bairro = enderecos.get("bairro");
        this.cidade = enderecos.get("localidade");
        this.UF = enderecos.get("uf");
    }

    // Transforma a string estruturada em um HashMap.
    private HashMap<String, String> parseJSON(String json) throws Exception {

        json = json.replaceAll("[{}]", "");

        Pattern keyValue = Pattern.compile("\"\\w*\": ?\"[\\w|\\s-]*\",?");
        Matcher m = keyValue.matcher(json);

        HashMap<String, String> properties = new HashMap<>();

        while (m.find()) {
            String result = m.group();
            result = result.replaceAll("[\",]", "");
            String[] values = result.split(":");
            properties.put(values[0], values[1]);
        }

        return properties;
    }

    // Consulta a API pública. Retorna o JSON resultante ou uma exceção em caso de erro. 
    private String getInfosAPI(String cep) throws Exception {
        StringBuilder sb = new StringBuilder();
        URL api = new URL("http://viacep.com.br/ws/" + cep + "/json");
        BufferedReader br = new BufferedReader(new InputStreamReader(api.openStream()));
        String line;

        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }

    /* 
        Getters e setters. Os campos que necessitam de validação junto a API 
        não possuem setters; o método setCep faz essa modificação    
     */
    public String getCep() {
        return cep;
    }

    public void setCep (String cep) throws Exception {
        this.cep = cep;
        preencheEndereco(parseJSON(getInfosAPI(cep)));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumEndereco() {
        return numEndereco;
    }

    public void setNumEndereco(String numEndereco) {
        this.numEndereco = numEndereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUF() {
        return UF;
    }
}
