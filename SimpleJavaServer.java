import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleJavaServer {
    public static void main(String[] args) 	{
        try {
            ServerSocket s = new ServerSocket(9999);
            String str,op;
            int op1, op2, calc;
            
            while (true) {
                Socket c = s.accept();
                InputStream i = c.getInputStream();
                OutputStream o = c.getOutputStream();

                do {
                    byte[] line = new byte[100];
                    i.read(line);
                    str = new String(line).trim();
                    try {
                        op1 = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        o.write("Numero invalido!".getBytes());
                    }

                    i.read(line);
                    if (!str.equals("+") && !str.equals("-") && !str.equals("*") && !str.equals("/")) {
                        o.write("Operador invalido!".getBytes());
                    } else {
                        o.write(str.getBytes());
                        op = str;
                    }

                    i.read(line);
                    try {
                        op2 = Integer.parseInt(str);
                    } catch (NumberFormatException e) {
                        o.write("Numero invalido!".getBytes());
                    }

                    switch (op) {
                        case "+":
                            calc = op1+op2;
                            break;
                        case "-":
                            calc = op1-op2;
                            break;
                        case "/":
                            calc = op1/op2;
                            break;
                        case "*":
                            calc = op1*op2;
                            break;
                        default:
                            o.write("Opcao invalida!");
                    }

                    if (calc != null) {
                        o.write("Resultado:"+String.valueOf(calc).getBytes());
                    }
                } while (!str.trim().equals("bye"));

                c.close();
            }
        }
        catch (Exception err){
            System.err.println(err);
        }
    }
}