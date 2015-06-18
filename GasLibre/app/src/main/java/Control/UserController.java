package Control;

import DAO.UserDAO;
import android.content.Context;
import android.util.Log;

import com.gaslibre.gaslibre.LoginScreen;

import Model.User;

/**
 * Created by renato on 6/15/15.
 */
public class UserController {

    private Context context;
    public static User userNaSessao= null;
    UserDAO userDao;

    public UserController() {
    }

    public UserController(Context context) {
        this.context = context;
        this.userDao= new UserDAO(context);
    }

    public boolean autenticaUsuario( String email, String senha ){
        User retorno= userDao.AutenticaUsuario(email, senha);
           if(retorno==null)
                return false;
        Log.v("USERBANCO;;;;;;;;",retorno.getId()+ "- "+ retorno.getName());
        return true;
    }

    public boolean registraUsuario(User userTemp){
        long a= userDao.registraUsuario(userTemp);

        return false;
    }

    public boolean estaLogadoNaSessao() {
        if(userNaSessao == null){
            return false;
        }else{
            return true;
        }
    }

    public void colocaUsuarioNaSessao(User user){
        userNaSessao= user;
    }
}
