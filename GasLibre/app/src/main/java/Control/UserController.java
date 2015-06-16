package Control;

import DAO.UserDAO;

/**
 * Created by renato on 6/15/15.
 */
public class UserController {

    UserDAO userDao= new UserDAO();

    public boolean autenticaUsuario( String email, String senha ){

        // pega retorno do banco
           userDao.AutenticaUsuario(email, senha);

        return false;

    }

    public boolean registraUsuario(String email, String senha){
        userDao.RegistraUsuario(email, senha);

        return false;
    }

    public boolean estaLogado() {
        //verifica sessao
        return false;
    }

}
