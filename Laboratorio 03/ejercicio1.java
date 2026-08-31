class Empleado{
    private String nombre;
    private double salario;
    private String departamento;
    protected double pagototal;
    protected double pagoadicional;
    
    Empleado(String nombre,double salario,String departamento){
        this.nombre=nombre;
        this.salario=salario;
        this.departamento=departamento;
    }
    
    public void CalcularPago(int diasTrabajados){
        pagototal=(salario/30)*diasTrabajados;
    }
    
    public void Mostrardatos(){
        System.out.println("nombre: "+nombre+
                        "\nsueldo x mes : "+salario+
                        "\ndepartamento: "+departamento+
                        "\nsalario: "+pagototal);
    }
    
    public void calcularHorasextras(int horasextras){
        pagoadicional=((salario/30)/8)*horasextras;
        System.out.println("trabajo "+horasextras+" horas extras,su pago por las horas es :"+pagoadicional);
    }
}
public class Main{
    public static void main(String[]args){
        Empleado emple1=new Empleado("kevin",1250.00,"secretaria");
        emple1.CalcularPago(23);
        emple1.Mostrardatos();
        emple1.calcularHorasextras(0);
    }
}
