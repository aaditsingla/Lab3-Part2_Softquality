package com.ontariotechu.sofe3980U;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BinaryAPIController {

    @GetMapping("/add")
    public String addString(
            @RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
            @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return Binary.add(number1, number2).getValue();
    }

    @GetMapping("/add_json")
    public BinaryAPIResult addJSON(
            @RequestParam(name = "operand1", required = false, defaultValue = "") String operand1,
            @RequestParam(name = "operand2", required = false, defaultValue = "") String operand2) {

        Binary number1 = new Binary(operand1);
        Binary number2 = new Binary(operand2);
        return new BinaryAPIResult(number1, "add", number2, Binary.add(number1, number2));
    }

    @GetMapping("/mul")
    public String mul(@RequestParam String operand1, @RequestParam String operand2) {
        Binary a = new Binary(operand1);
        Binary b = new Binary(operand2);
        return Binary.multiply(a, b).getValue();
    }

    @GetMapping("/mul_json")
    public BinaryAPIResult mulJson(@RequestParam String operand1, @RequestParam String operand2) {
        Binary a = new Binary(operand1);
        Binary b = new Binary(operand2);
        return new BinaryAPIResult(a, "mul", b, Binary.multiply(a, b));
    }

    @GetMapping("/and")
    public String and(@RequestParam String operand1, @RequestParam String operand2) {
        Binary a = new Binary(operand1);
        Binary b = new Binary(operand2);
        return Binary.and(a, b).getValue();
    }

    @GetMapping("/and_json")
    public BinaryAPIResult andJson(@RequestParam String operand1, @RequestParam String operand2) {
        Binary a = new Binary(operand1);
        Binary b = new Binary(operand2);
        return new BinaryAPIResult(a, "and", b, Binary.and(a, b));
    }

    @GetMapping("/or")
    public String or(@RequestParam String operand1, @RequestParam String operand2) {
        Binary a = new Binary(operand1);
        Binary b = new Binary(operand2);
        return Binary.or(a, b).getValue();
    }

    @GetMapping("/or_json")
    public BinaryAPIResult orJson(@RequestParam String operand1, @RequestParam String operand2) {
        Binary a = new Binary(operand1);
        Binary b = new Binary(operand2);
        return new BinaryAPIResult(a, "or", b, Binary.or(a, b));
    }
}
