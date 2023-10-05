
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.Empleado;
import modelo.EmpleadoDAO;
import sun.security.pkcs11.wrapper.Functions;


public class Validar extends HttpServlet {
    private Empleado objEmpleado = new Empleado();
    private EmpleadoDAO objEmpleadoDAO = new EmpleadoDAO();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String accion = request.getParameter("btnEnviar");
        String testUser = request.getParameter("txtUser");
        String testPass = request.getParameter("txtPassword");
        if(accion.equals("Ingresar")){
            objEmpleado = objEmpleadoDAO.validar(testUser, testPass);
            if(objEmpleado.getUsuario() != null){
                request.setAttribute("empleado", objEmpleado);
                
                //pasar el id del empleado que inició la sesión al Controlador
                HttpSession sesion = request.getSession();
                sesion.setAttribute("id_empleado_sesion", objEmpleado.getIdEmpleado());
                
                request.getRequestDispatcher("Controlador?menu=Principal").forward(request, response);
            }
            else{
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
