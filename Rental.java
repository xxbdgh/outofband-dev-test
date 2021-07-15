package ChouXiang;

import com.sun.xml.internal.ws.addressing.WsaActionUtil;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import jdk.nashorn.internal.runtime.regexp.joni.ScanEnvironment;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
/*
2.	 设计一个租车系统,可以租轿车和卡车
     租轿车的租金计算方法：   <=7天   日租金*天数
                           >7天    超出7天部分打7折
     租卡车的租金计算方法:    <=10天  载重量*50*天数
                           >10天   超出10天安部分打6折
     功能包含
> 1.新车入库
> 2.租车(计算租金)
> 3.还车
 */
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
public class Rental {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Function function =null;
        do { CarSystem();
          Scanner s = new Scanner(System.in);
          int num = s.nextInt();
          switch (num){
              case 1:
                  function = new Function1();
                  function.ruKu();
                  break;
              case 2:
                  function = new Function2();
                  function.calculationRent();
                  break;
              case 3:
                  function = new Function3();
                  function.returnCar();
                  break;
              case 4:
                  System.out.println("轿车车辆为："+function.getCarNum());
                  System.out.println("卡车车辆为："+function.getTruckNum());
                  break;
             }
        }while(true);
    }
    static void CarSystem(){
        ArrayList<String> carSystem=new ArrayList<>();
        Collections.addAll(carSystem,"========================="+"\n"+"欢迎光临租车系统："+"\n"+"请选择功能："+"\n"+"1.新车入库\n"+"2.租车(计算租金)"
        +"\n3.还车"+"\n4.查询总车辆");
        for (String element :carSystem) {
            System.out.println(element);
        }
    }
}
class Function{
    private int carNum=10;
    private int truckNum=10;
    private int carRent=60;
    public Function(){

    }
    public Function(int carNum, int truckNum, int carRent) {
        this.carNum = carNum;
        this.truckNum = truckNum;
        this.carRent = carRent;
    }
    public int getCarNum() {
        return carNum;
    }

    public void setCarNum(int carNum) {
        this.carNum = carNum;
    }

    public int getTruckNum() { return truckNum; }

    public void setTruckNum(int truckNum) {
        this.truckNum = truckNum;
    }

    public int getCarRent() {
        return carRent;
    }

    public void setCarRent(int carRent) {
        this.carRent = carRent;
    }

    void ruKu()throws  InterruptedException, ExecutionException{}

    void calculationRent() throws InterruptedException {
    }
    void returnCar() throws InterruptedException {
    }
}
class Function1 extends Function{
    public Function1() {
        super();
    }
    void ruKu()throws  InterruptedException, ExecutionException {
        System.out.println("请输入要入库的新车类型：(1.轿车  2.卡车)");
        Scanner s = new Scanner(System.in);
        int num = s.nextInt();
        switch (num) {
            case 1:
                System.out.println("请输入入库的轿车数量");
                int car = s.nextInt();
                setCarNum(getCarNum()+car);
                System.out.println("添加成功！！正在查询现有车辆,请稍后。。。");
                Thread.sleep(1000);
                System.out.println("现有轿车："+getCarNum());
                System.out.println("现有卡车："+getTruckNum());
                break;
            case 2:
                System.out.println("请输入入库的卡车数量");
                int Truck = s.nextInt();
                setTruckNum(getTruckNum()+Truck);
                System.out.println("添加成功！！正在查询现有车辆,请稍后。。。");
                Thread.sleep(1000);
                System.out.println("现有轿车："+getCarNum());
                System.out.println("现有卡车："+getTruckNum());
                break;
            }
        }
    }

    class Function2 extends Function{
        public Function2() {
            super();
        }
        void calculationRent() throws InterruptedException {
            System.out.println("请输入需要租车的类型(1.轿车  2.卡车 )");
            Scanner s = new Scanner(System.in);
            int num = s.nextInt();
            switch (num){
                case 1:
                    System.out.println("请了解轿车租金的计算方式："+"\n"+"租的天数<=7天 每天60元。租的天数>7天  超出7天部分打7折");
                    System.out.println("请确认(Yes/No):");
                    String  a = s.next();
                    int e=0;
                    if (a.equalsIgnoreCase("yes")){
                        System.out.println("请输入需要轿车的数量："+"剩余轿车"+"("+getCarNum()+")");
                        int b = s.nextInt();
                        if (b>super.getCarNum()){
                            System.out.println("输入错误，返回主界面");
                            return;
                        }
                        System.out.println("请输入需要租的天数：");
                        int c = s.nextInt();
                        if (c>7){
                            e=(int)((c-7)*60*0.7);
                        }
                        System.out.println("正在计算需要支付的租金，请稍后。。。");
                        for (int i = 0; i <3 ; i++) {
                            System.out.print("。"+"  ");
                            Thread.sleep(500);
                        }
                        System.out.println("您需要支付租金【"+((60*b*c)+e)+"】元，请输入需要支付的金额，以完成交易：");
                        int d = s.nextInt();
                        if (d==((60*b*c)+e)){
                            System.out.println("支付成功，期待您的下次光临！！！");
                            super.setCarNum(super.getCarNum()-b);
                            Thread.sleep(500);
                            System.out.println("正在返回主界面。。。");
                            Thread.sleep(1000);
                        }else {
                            System.out.println("支付失败，正在返回主界面。。。");
                            Thread.sleep(1000);
                            return;
                        }
                    }else if(a.equalsIgnoreCase("no")){
                        System.out.println("正在返回主界面");
                        return;
                    }else {
                        if ((a.equalsIgnoreCase("yes")!=true)&&(a.equalsIgnoreCase("no")!=true)){
                            System.out.println("输入内容不合法，正在返回主界面");
                            return;
                        }
                    }
                    break;
                case 2:
                    System.out.println("请了解卡车租金的计算方式："+"\n"+"(每辆卡车均重1.5吨) 天数<=10天  50*车重*天数。 >10天 超出10天部分打6折");
                    System.out.println("请确认(Yes/No):");
                    String  truck1 = s.next();
                    int f=0;
                    if (truck1.equalsIgnoreCase("yes")){
                        System.out.println("请输入需要卡车的数量：(库存卡车："+super.getTruckNum()+"辆)");
                        int b = s.nextInt();
                        if (b>getTruckNum()){
                            System.out.println("数量输入不正确，正在返回主界面");
                            return;
                        }
                        System.out.println("请输入需要租的天数：");
                        int c = s.nextInt();
                        if (c>7){
                            f=(int)(50*1.5*(c-10)*b*0.6);
                        }
                        System.out.println("正在计算需要支付的租金，请稍后。。。");
                        for (int i = 0; i <3 ; i++) {
                            System.out.print("。"+"  ");
                            Thread.sleep(500);
                        }
                        System.out.println("您需要支付租金【"+((50*1.5*c*b)+f)+"】元，请输入需要支付的金额，以完成交易：");
                        int d = s.nextInt();
                        if (d==((50*1.5*c*b)+f)){
                            System.out.println("支付成功，期待您的下次光临！！！");
                            Thread.sleep(500);
                            super.setTruckNum(super.getTruckNum()-b);
                            System.out.println("正在返回主界面。。。");
                            Thread.sleep(1000);
                        }else {
                            System.out.println("支付失败，正在返回主界面。。。");
                            Thread.sleep(1000);
                        }
                    }else if(truck1.equalsIgnoreCase("no")){
                        System.out.println("正在返回主界面");
                    }else {
                        if ((truck1.equalsIgnoreCase("yes")!=true)&&(truck1.equalsIgnoreCase("no")!=true)){
                            System.out.println("输入内容不合法，正在返回主界面");
                        }
                    }
                    break;
        }
    }
}
class Function3 extends Function {
        public Function3() {
            super();
        }
        void returnCar() throws InterruptedException {
            Scanner s = new Scanner(System.in);
            System.out.println("请输入想要归还的车辆类型：(1.轿车 2.卡车)");
            int a = s.nextInt();
            switch (a){
                case 1 :
                    System.out.println("请输入需要归还车辆的数量(轿车)：");
                    int b = s.nextInt();
                    System.out.println("归还成功！！");
                    System.out.println("正在计算：");
                    Thread.sleep(1000);
                    System.out.println("当前轿车车辆剩余【"+(super.getCarNum()+b)+"】辆");
                    break;
                case 2:
                    System.out.println("请输入需要归还车辆的数量(卡车)：");
                    int c = s.nextInt();
                    System.out.println("正在计算：");
                    Thread.sleep(1000);
                    System.out.println("当前卡车车辆剩余【"+(super.getTruckNum()+c)+"】辆");
                    break;
            }
        }
}