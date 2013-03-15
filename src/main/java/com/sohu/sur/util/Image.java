package com.sohu.sur.util;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.*;
/**
 *
 * @author http://www.tot.name
 * @version
 */
public class Image extends HttpServlet {

	private static String base = "一乙二十丁厂七卜人入八九几儿了力乃刀又三于干亏士工土才寸下大丈与万上小口巾山千乞川亿个勺久凡及夕丸么广亡门义之尸弓己已子卫也女飞刃习叉马乡丰王井开夫天无元专云扎艺木五支厅不太犬区历尤友匹车巨牙屯比互切瓦止少日中冈贝内水见午牛手毛气升长仁什片仆化仇币仍仅斤爪反介父从今凶分乏公仓月氏勿欠风丹匀乌凤勾文六方火为斗忆订计户认心尺引丑巴孔队办以允予劝双书幻玉刊示末未击打巧正扑扒功扔去甘世古节本术可丙左厉右石布龙平灭轧东卡北占业旧帅归且旦目叶甲申叮电号田由史只央兄叼叫另叨叹四生失禾丘付仗代仙们仪白仔他斥瓜乎丛令用甩印乐句匆册犯外处冬鸟务包饥主市立闪兰半汁汇头汉宁穴它讨写让礼训必议讯记永司尼民出辽奶奴加召皮边发孕圣对台矛纠母幼丝式刑动扛寺吉扣考托老执巩圾扩扫地扬场耳共芒亚芝朽朴机权过臣再协西压厌在有百存而页匠夸夺灰达列死成夹轨邪划迈毕至此贞师尘尖劣光当早吐吓虫曲团同吊吃因吸吗屿帆岁回岂刚则肉网年朱先丢舌竹迁乔伟传乒乓休伍伏优伐延件任伤价份华仰仿伙伪自血向似后行舟全会杀合兆企众爷伞创肌朵杂危旬旨负各名多争色壮冲冰庄庆亦刘齐交次衣产决充妄闭问闯羊并关米灯州汗污江池汤忙兴宇守宅字安讲军许论农讽设访寻那迅尽导异孙阵阳收阶阴防奸如妇好她妈戏羽观欢买红纤级约纪驰巡寿弄麦形进戒吞远违运扶抚坛技坏扰拒找批扯址走抄坝贡攻赤折抓扮抢孝均抛投坟抗坑坊抖护壳志扭块声把报却劫芽花芹芬苍芳严芦劳克苏杆杠杜材村杏极李杨求更束豆两丽医辰励否还歼来连步坚旱盯呈时吴助县里呆园旷围呀吨足邮男困吵串员听吩吹呜吧吼别岗帐财针钉告我乱利秃秀私每兵估体何但伸作伯伶佣低你住位伴身皂佛近彻役返余希坐谷妥含邻岔肝肚肠龟免狂犹角删条卵岛迎饭饮系言冻状亩况床库疗应冷这序辛弃冶忘闲间闷判灶灿弟汪沙汽沃泛沟没沈沉怀忧快完宋宏牢究穷灾良证启评补初社识诉诊词译君灵即层尿尾迟局改张忌际陆阿陈阻附妙妖妨努忍劲鸡驱纯纱纳纲驳纵纷纸纹纺驴纽奉玩环武青责现表规抹拢拔拣担坦押抽拐拖拍者顶拆拥抵拘势抱垃拉拦拌幸招坡披拨择抬其取苦若茂苹苗英范直茄茎茅林枝杯柜析板松枪构杰述枕丧或画卧事刺枣雨卖矿码厕奔奇奋态欧垄妻轰顷转斩轮软到非叔肯齿些虎虏肾贤尚旺具果味昆国昌畅明易昂典固忠咐呼鸣咏呢岸岩帖罗帜岭凯败贩购图钓制知垂牧物乖刮秆和季委佳侍供使例版侄侦侧凭侨佩货依的迫质欣征往爬彼径所舍金命斧爸采受乳贪念贫肤肺肢肿胀朋股肥服胁周昏鱼兔狐忽狗备饰饱饲变京享店夜庙府底剂郊废净盲放刻育闸闹郑券卷单炒炊炕炎炉沫浅法泄河沾泪油泊沿泡注泻泳泥沸波泼泽治怖性怕怜怪学宝宗定宜审宙官空帘实试郎诗肩房诚衬衫视话诞询该详建肃录隶居届刷屈弦承孟孤陕降限妹姑姐姓始驾参艰线练组细驶织终驻驼绍经贯奏春帮珍玻毒型挂封持项垮挎城挠政赴赵挡挺括拴拾挑指垫挣挤拼挖按挥挪某甚革荐巷带草茧茶荒茫荡荣故胡南药标枯柄栋相查柏柳柱柿栏树要咸威歪研砖厘厚砌砍面耐耍牵残殃轻鸦皆背战点临览竖省削尝是盼眨哄显哑冒映星昨畏趴胃贵界虹虾蚁思蚂虽品咽骂哗咱响哈咬咳哪炭峡罚贱贴骨钞钟钢钥钩卸缸拜看矩怎牲选适秒香种秋科重复竿段便俩贷顺修保促侮俭俗俘信皇泉鬼侵追俊盾待律很须叙剑逃食盆胆胜胞胖脉勉狭狮独狡狱狠贸怨急饶蚀饺饼弯将奖哀亭亮度迹庭疮疯疫疤姿亲音帝施闻阀阁差养美姜叛送类迷前首逆总炼炸炮烂剃洁洪洒浇浊洞测洗活派洽染济洋洲浑浓津恒恢恰恼恨举觉宣室宫宪突穿窃客冠语扁袄祖神祝误诱说诵垦退既屋昼费陡眉孩除险院娃姥姨姻娇怒架贺盈勇怠柔垒绑绒结绕骄绘给络骆绝绞统耕耗艳泰珠班素蚕顽盏匪捞栽捕振载赶起盐捎捏埋捉捆捐损都哲逝捡换挽热恐壶挨耻耽恭莲莫荷获晋恶真框桂档桐株桥桃格校核样根索哥速逗栗配翅辱唇夏础破原套逐烈殊顾轿较顿毙致柴桌虑监紧党晒眠晓鸭晃晌晕蚊哨哭恩唤啊唉罢峰圆贼贿钱钳钻铁铃铅缺氧特牺造乘敌秤租积秧秩称秘透笔笑笋债借值倚倾倒倘俱倡候俯倍倦健臭射躬息徒徐舰舱般航途拿爹爱颂翁脆脂胸胳脏胶脑狸狼逢留皱饿恋桨浆衰高席准座脊症病疾疼疲效离唐资凉站剖竞部旁旅畜阅羞瓶拳粉料益兼烤烘烦烧烛烟递涛浙涝酒涉消浩海涂浴浮流润浪浸涨烫涌悟悄悔悦害宽家宵宴宾窄容宰案请朗诸读扇袜袖袍被祥课谁调冤谅谈谊剥恳展剧屑弱陵陶陷陪娱娘通能难预桑绢绣验继球理捧堵描域掩捷排掉堆推掀授教掏掠培接控探据掘职基著勒黄萌萝菌菜萄菊萍菠营械梦梢梅检梳梯桶救副票戚爽聋袭盛雪辅辆虚雀堂常匙晨睁眯眼悬野啦晚啄距跃略蛇累唱患唯崖崭崇圈铜铲银甜梨犁移笨笼笛符第敏做袋悠偿偶偷您售停偏假得衔盘船斜盒鸽悉欲彩领脚脖脸脱象够猜猪猎猫猛馅馆凑减毫麻痒痕廊康庸鹿盗章竟商族旋望率着盖粘粗粒断剪兽清添淋淹渠渐混渔淘液淡深婆梁渗情惜惭悼惧惕惊惨惯寇寄宿窑密谋谎祸谜逮敢屠弹随蛋隆隐婚婶颈绩绪续骑绳维绵绸绿琴斑替款堪搭塔越趁趋超提堤博揭喜插揪搜煮援裁搁搂搅握揉斯期欺联散惹葬葛董葡敬葱落朝辜葵棒棋植森椅椒棵棍棉棚棕惠惑逼厨厦硬确雁殖裂雄暂雅辈悲紫辉敞赏掌晴暑最量喷晶喇遇喊景践跌跑遗蛙蛛蜓喝喂喘喉幅帽赌赔黑铸铺链销锁锄锅锈锋锐短智毯鹅剩稍程稀税筐等筑策筒答筋筝傲傅牌堡集焦傍储奥街惩御循艇舒番释禽腊脾腔鲁猾猴然馋装蛮就痛童阔善羡普粪尊道曾焰港湖渣湿温渴滑湾渡游滋溉愤慌惰愧愉慨割寒富窜窝窗遍裕裤裙谢谣谦属屡强粥疏隔隙絮嫂登缎缓编骗缘瑞魂";
	
	private static String idiom = "平易近人,宽宏大度,冰清玉洁,持之以恒,锲而不舍,废寝忘食,大义凛然,临危不俱,光明磊落,不屈不挠,鞠躬尽瘁,料事如神,足智多谋,融会贯通,学贯中西,博古通今,才华横溢,出类拔萃,博大精深,集思广益,举一反三,文质彬彬,风度翩翩,相貌堂堂,落落大方,斗志昂扬,意气风发,威风凛凛,容光焕发,神采奕奕,眉飞色舞,神采奕奕,欣喜若狂,呆若木鸡,喜出望外,无动于衷,能言善辩,滔滔不绝,出口成章,语惊四座,妙语连珠,口若悬河,三顾茅庐,铁杵成针,望梅止渴,完璧归赵,四面楚歌,精忠报国,走马观花,手舞足蹈,奔走相告,跋山涉水,前赴后继,恩重如山,深情厚谊,手足情深,形影不离,血浓于水,志同道合,风雨同舟,赤诚相待,肝胆相照,生死相依,循序渐进,日积月累,温故知新,学无止境,学海无涯,发奋图强,画龙点睛,守株待兔,亡羊补牢,无懈可击,锐不可当,雷厉风行,惊心动魄,铺天盖地,势如破竹,气贯长虹,万马奔腾,如履平地,春暖花开,春华秋实,春回大地,春雨绵绵,百花齐放,百鸟争鸣,骄阳似火,烈日炎炎,花繁叶茂,热火朝天,五谷丰登,秋高气爽,冰天雪地,寒气袭人,济济一堂,门庭若市,高朋满座,如火如荼,蒸蒸日上,欣欣向荣,川流不息,美不胜收,富丽堂皇,金碧辉煌,美妙绝伦,巧夺天工,锦上添花,别有洞天,锦绣河山,高耸入云,水天一色,波光粼粼,湖光山色,山明水秀,高山流水,繁花似锦,绿草如茵,郁郁葱葱,古树参天,百花齐放,万紫千红,桃红柳绿,绿树成荫,皓月千里,暴风骤雨,风驰电掣,细雨如丝,月光如水,聚精会神,全神贯注,目不转睛,闭目养神,无私奉献,舍己为人,见义勇为,大公无私,不屈不饶,顽强不屈,月明星稀,万家灯火,自言自语,对答如流,滔滔不绝,浓眉大眼,喜上眉梢,眉飞色舞,眉开眼笑,一丝不苟,专心致志,聚精会神,勤学苦练,如饥似渴,持之以恒,日积月累,再接再厉,古色古香,金碧辉煌,小巧玲珑,栩栩如生,满天繁星,群星闪烁,众星拱月,艳阳高照,阳光明媚,心花怒放,惊喜万分,兴高采烈,心旷神怡,喜出望外,欢天喜地,怒气冲天,欢蹦乱跳,山青水秀,千山万水,青山绿水,兴国安邦,翻山越岭,百依百顺,调兵遣将,甜言蜜语,眼疾手快,天长地久,芸芸众生,咄咄逼人,头头是道,津津有味,津津乐道,奄奄一息,念念不忘,空空如也,源源不绝,姗姗来迟,面面相觑,面面俱到,振振有辞,窃窃私语,息息相关,喋喋不休,循循善诱,郁郁寡欢,彬彬有礼,亭亭玉立,铮铮铁骨,飘飘欲仙,夸夸其谈,孜孜以求,孜孜不倦,莘莘学子,跃跃欲试,井井有条,绰绰有余,楚楚可怜,混水摸鱼,龙盘虎踞,包罗万象,车水马龙,九牛二虎,虎虎生威,虎口拔牙,生龙活虎,龙马精神,龙飞凤舞,龙争虎斗,马到功成,一丝不苟,五湖四海,百家争鸣,千山万水,千辛万苦,独树一帜,五体投地,十万火急,十拿九稳,一本正经,二话不说,三心二意,四面八方,五光十色,六神无主,七上八下,八仙过海,九牛一毛,十全十美,天昏地暗,前因后果,博古通今,铺天盖地,鸦雀无声,雅俗共赏,言行一致,养尊处优,一帆风顺,一见如故,一举两得,一劳永逸,一落千丈,一目了然,一视同仁,一网打尽,一针见血,以卵击石,以身作则,易如反掌,异想天开,饮水思源,迎刃而解,应接不暇,有目共睹,与日俱增,再接再厉,真知灼见,蒸蒸日上,趾高气扬,志同道合,置之不理,忠言逆耳,自力更生,自相矛盾";
    /** Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("image/jpeg");
        response.setHeader("Pragma","No-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires", 0);       
        HttpSession session=request.getSession();
        // 在内存中创建图象
        int width=118, height=31;
        
        //int length = base.length();
        String words[] = idiom.split(",");
        int length = words.length;
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        // 获取图形上下文
        Graphics g = image.getGraphics();

        //生成随机类
        Random random = new Random();

        // 设定背景色
        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);

        //设定字体
        //g.setFont(new Font("Times New Roman",Font.PLAIN,18));
        String[] fontTypes = { "\u5b8b\u4f53" , "\u9ed1\u4f53" , "\u65b0\u5b8b\u4f53" ,"\u6977\u4f53" , "\u96b6\u4e66" };
        int fontTypesLength = fontTypes.length;
        
        //画边框
        //g.setColor(new Color());
        //g.drawRect(0,0,width-1,height-1);


        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(getRandColor(160,200));
        for (int i=0;i<155;i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }
        
     // 在图片背景上增加噪点，增加图片分析难度 
       g.setFont( new Font( "Times New Roman" , Font.PLAIN, 14)); 
       for ( int i = 0; i < 4; i++) { 
         g.drawString( "@*@^*@$*@*@*^*@*@*" ,0, 5 * (i + 2)); 
       }         

        // 取随机产生的认证码(4位数字)
        String sRand="";
//        for (int i=0;i<4;i++){
//            int start = random.nextInt(length);
//            String rand = base.substring(start, start + 1);
//            sRand += rand;
//            // 设置图片上字体的颜色
//            g.setColor(getRandColor(random, 10, 150));
//            // 设置字体格式
//            g.setFont( new Font(fontTypes[random.nextInt(fontTypesLength)],
//            Font.BOLD, 18 + random.nextInt(6)));
//            // 将此汉字画到验证图片上面
//            g.drawString(rand, 22 * i + 11 + random.nextInt(8), 24);
//            
//        }
        int index = random.nextInt(length);
        sRand = words[index];
        for (int i = 0; i < sRand.length(); i++) {
        	String rand = String.valueOf(sRand.charAt(i));
        	// 设置图片上字体的颜色
            g.setColor(getRandColor(random, 10, 150));
            // 设置字体格式
            g.setFont( new Font(fontTypes[random.nextInt(fontTypesLength)],
            Font.BOLD, 18 + random.nextInt(6)));
            // 将此汉字画到验证图片上面
            g.drawString(rand, 22 * i + 11 + random.nextInt(8), 24);
        }

        // 将认证码存入SESSION
        session.setAttribute("rand",sRand);
        // 图象生效
        g.dispose();
        ServletOutputStream responseOutputStream =response.getOutputStream();
        // 输出图象到页面
        ImageIO.write(image, "JPEG", responseOutputStream);

        //以下关闭输入流！
        responseOutputStream.flush();
        responseOutputStream.close();
    }
    
    Color getRandColor(int fc,int bc){//给定范围获得随机颜色
        Random random = new Random();
        if(fc>255) fc=255;
        if(bc>255) bc=255;
        int r=fc+random.nextInt(bc-fc);
        int g=fc+random.nextInt(bc-fc);
        int b=fc+random.nextInt(bc-fc);
        return new Color(r,g,b);
    }
    
    Color getRandColor(Random random, int ff, int cc) {
        if (ff > 255)
            ff = 255;
        if (cc > 255)
            cc = 255;
        int r = ff + random.nextInt(cc - ff);
        int g = ff + random.nextInt(cc - ff);
        int b = ff + random.nextInt(cc - ff);
        return new Color(r, g, b);
     } 
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** Returns a short description of the servlet.
     */
    public String getServletInfo() {
        return "Short description";
    }
    // </editor-fold>
}

