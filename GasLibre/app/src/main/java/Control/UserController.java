package Control;

import DAO.UserDAO;
import android.content.Context;
import Model.User;

/**
 * Created by renato on 6/15/15.
 */
public class UserController {

    Context context;

    UserDAO userDao= new UserDAO(context);

    public boolean autenticaUsuario( String email, String senha ){
           if(userDao.AutenticaUsuario(email, senha) == null)
                return false;
        return true;
    }

    public boolean registraUsuario(String name, String email, String senha){
        User userTemp= new User(name, email, senha);
        if(userDao.registraUsuario(userTemp) != -1)
            return true;
        return false;
    }

    public boolean estaLogadoNaSessao() {
        //verifica sessao
        return false;
    }

    public void colocaUsuarioNaSessao(){
        //
    }
}
