package pessoas;

import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pessoa;

public class Main {

    public static void main(String[] args) {
        try {
            Pessoa p = new Pessoa("nome", "cpf", "telefone", "88301000", "42", "comp");
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
