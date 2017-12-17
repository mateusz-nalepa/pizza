package pl.tu.kielce.pizza.fe.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.tu.kielce.pizza.common.messages.MessageSourceAccessor;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Autowired
    private final MessageSourceAccessor messageSourceAccessor;

    @RequestMapping(PATH)
    public String renderErrorPage(HttpServletRequest httpRequest, Model model) {

        String errorMsg;
        int httpErrorCode = getErrorCode(httpRequest);

        switch (httpErrorCode) {
            case 401: {
                errorMsg = "error.unauthorized";
                break;
            }
            case 404: {
                errorMsg = "error.pageNotFound";
                break;
            }
            case 500: {
                errorMsg = "error.internalServerError";
                break;
            }
            default: {
                errorMsg = "error.unknownError";
                break;
            }
        }
        model.addAttribute("errorMsg", errorMsg);
        return "error/error-page";
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
