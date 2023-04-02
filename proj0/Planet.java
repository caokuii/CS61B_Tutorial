public class Planet {
    double xxPos;//Its current x position
    double yyPos;//Its current y position
    double xxVel;// Its current velocity速度 in the x direction
    double yyVel;// Its current velocity in the y direction
    double mass;// Its mass质量
    String imgFileName;// The name of the file that corresponds to the image that depicts the planet (for example, jupiter.gif)
    public static double G = 6.67e-11;
    //constructor
    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    //the second constructor
    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }

    //计算一个planet和另一个planet的距离
    public double calcDistance(Planet p){
        double d;
        d = (this.xxPos - p.xxPos)*(this.xxPos - p.xxPos) + (this.yyPos - p.yyPos)*(this.yyPos - p.yyPos);
        d = Math.sqrt(d);
        return d;
    }

    //计算指定planet对该planet的力
    public double calcForceExertedBy(Planet p) {
        if(this.equals(p)){
            return 0;
        }
        else{
            double f;
            double d = this.calcDistance(p);//调用类中的计算距离的函数
            f = (G * this.mass * p.mass) / (d * d);
            return f;
        }
    }

    //计算指定planet对该planet在X 轴上的力
    public double calcForceExertedByX(Planet p){
        if(this.equals(p)){
            return 0;
        }
        else{
            double fX = (this.calcForceExertedBy(p) * (p.xxPos - this.xxPos)) / this.calcDistance(p);
            return fX;
        }
    }

    //计算指定planet对该planet在Y 轴上的力
    public double calcForceExertedByY(Planet p){
        if(this.equals(p)){
            return 0;
        }
        else{
            double fY = (this.calcForceExertedBy(p) * (p.yyPos - this.yyPos)) / this.calcDistance(p);
            return fY;
        }
    }

    //计算指定planet 数组对该planet 在X 轴上的合力
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double fNetX = 0;
        int i;
        //设定Planet自身之间不能产生引力
        for(i = 0; i < allPlanets.length; i++){
            fNetX += this.calcForceExertedByX(allPlanets[i]);
        }
        return fNetX;
    }

    //计算指定planet 数组对该planet 在Y 轴上的合力
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double fNetY = 0;
        int i;
        //设定Planet自身之间不能产生引力
        for(i = 0; i < allPlanets.length; i++){
            if(this.equals(allPlanets)){
                continue;
            }
            else{
                fNetY += this.calcForceExertedByY(allPlanets[i]);
            }
        }
        return fNetY;
    }

    //在X坐标下的力fX和Y坐标的作用下持续dt的时间，计算Planet的新状态：新速度、新位置
    public void update(double dt, double fX, double fY){
        //第一步计算加速度
        double aX = fX / this.mass, aY = fY / this.mass;
        //第二步计算新的速度
        xxVel = xxVel + aX * dt;
        yyVel = yyVel + aY * dt;
        //第三步计算新的位置
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    //使用StdDraw API 绘制该planet
    public void draw(){
        String s1 = "./images/";
        String imagePath = s1 + "" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, imagePath);
    }

}
