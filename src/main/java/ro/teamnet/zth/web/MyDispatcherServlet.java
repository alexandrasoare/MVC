package ro.teamnet.zth.web;

import ro.teamnet.zth.api.annotations.MyController;
import ro.teamnet.zth.api.annotations.MyRequestMethod;
import ro.teamnet.zth.appl.controller.DepartmentController;
import ro.teamnet.zth.appl.controller.EmployeeController;
import ro.teamnet.zth.appl.domain.Department;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandra.Soare on 7/20/2017.
 */
public class MyDispatcherServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //resp.getWriter().write("dfghj");
        dispatchReply(req, resp, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private Object dispatch(HttpServletRequest request) {
//        EmployeeController employeeController = new EmployeeController();
//        DepartmentController departmentController = new DepartmentController();
//        String val = null;
//
//        if (request.getRequestURI().contains("/employees")) {
//            if (request.getRequestURI().contains("/all"))
//                val = employeeController.getAllEmployees();
//            if (request.getRequestURI().contains("/one"))
//                val = employeeController.getOneEmployee();
//        } else if (request.getRequestURI().contains("/departments")) {
//            if (request.getRequestURI().contains("/all"))
//                val = departmentController.getAllDepartments();
//            if (request.getRequestURI().contains("/one"))
//                val = departmentController.getOneDepartments();
//        }
//        return val;
        EmployeeController employeeController = new EmployeeController();
        DepartmentController departmentController = new DepartmentController();
        MyController controller = request.getClass().getDeclaredAnnotation(MyController.class);
        String val = null;

        if (controller.urlPath().equals("/employees")) {
            MyRequestMethod requestMethod = request.getClass().getDeclaredAnnotation(MyRequestMethod.class);

            if (requestMethod.urlPath().equals("/all"))
                val = employeeController.getAllEmployees();
            if (requestMethod.urlPath().equals("/one"))
                val = employeeController.getOneEmployee();
        }
        if (controller.urlPath().equals("/departments")) {
            MyRequestMethod requestMethod = request.getClass().getDeclaredAnnotation(MyRequestMethod.class);

            if (requestMethod.urlPath().equals("/all"))
                val = departmentController.getAllDepartments();
            if (requestMethod.urlPath().equals("/one"))
                val = departmentController.getOneDepartments();
        }
        return val;
    }


    private void reply(HttpServletResponse response, Object result) {
        try {
            response.getWriter().write((String)result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendExceptionError() {

    }

    private void dispatchReply(HttpServletRequest request, HttpServletResponse response, String type) {
        try {
            Object resultToDisplay = dispatch(request);
            reply(response, resultToDisplay);
        } catch (Exception e) {
            sendExceptionError();
        }
    }
}
