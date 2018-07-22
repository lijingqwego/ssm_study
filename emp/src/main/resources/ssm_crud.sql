/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : ssm_crud

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2018-07-22 22:25:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) NOT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '贾家荣国府');
INSERT INTO `t_dept` VALUES ('2', '贾家宁国府');
INSERT INTO `t_dept` VALUES ('3', '史家');
INSERT INTO `t_dept` VALUES ('4', '王家');
INSERT INTO `t_dept` VALUES ('5', '薛家');
INSERT INTO `t_dept` VALUES ('6', '林家');
INSERT INTO `t_dept` VALUES ('7', '皇家');
INSERT INTO `t_dept` VALUES ('8', '其他');
INSERT INTO `t_dept` VALUES ('9', '甄家');
INSERT INTO `t_dept` VALUES ('10', '贾家');

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_name` varchar(255) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `d_id` int(11) DEFAULT NULL,
  `p_id` int(11) DEFAULT NULL,
  `description` text,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `emp_name` (`emp_name`),
  KEY `fk_emp_dept` (`d_id`),
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`d_id`) REFERENCES `t_dept` (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES ('32', '贾演', 'M', 'jiayan@kaisn.com', '2', '11', '贾演是我国著名长篇小说《红楼梦》中的一个人物，荣国公贾源的胞兄，被朝廷封为宁国公，底下有四子（书中仅提及长子贾代化），死后由长子贾代化继承其爵位，贾演与贾源为贾府家业的开创者', '2018-07-21 19:59:05');
INSERT INTO `t_emp` VALUES ('33', '贾代化', 'M', 'jiadaihua@kaisn.com', '2', '11', '贾代化是曹雪芹名著《红楼梦》中的虚构人物。贾演长子，贾敷、贾敬之父，京营节度使世袭一等神威将军。《红楼梦》第二回 [1]  中便提到此人物', '2018-07-21 20:00:38');
INSERT INTO `t_emp` VALUES ('34', '贾敬', 'M', 'jiajing@kaisn.com', '2', '11', '贾敬，中国古典小说曹雪芹写的《红楼梦》中的人物，宁国公贾演的孙子，京营节度使世袭一等神威将军贾代化的次子，贾珍之父。是乙卯科进士，却一味好道，在都外玄真观修炼，烧丹炼汞，别的事一概不管，放纵家人胡作非为。后因吃秘制的丹砂烧胀而死。死后追赐为五品之职。', '2018-07-21 20:01:43');
INSERT INTO `t_emp` VALUES ('35', '贾珍', 'M', 'jianzheng@kaisn.com', '2', '11', '贾珍，中国古典小说《红楼梦》中的人物，贾敬之子，贾演曾孙。世袭三品爵威烈将军。生活穷奢极欲，虽有一妻二妾，但仍和儿媳秦可卿、妻妹尤二姐关系暧昧', '2018-07-21 20:03:23');
INSERT INTO `t_emp` VALUES ('36', '贾惜春', 'F', 'jiaxichun@kaisn.com', '2', '4', '贾惜春是古典名著《红楼梦》中人物，金陵十二钗之一，她是贾家四姐妹中年纪最小的一位，宁国府中贾珍的胞妹。她父亲贾敬在书中开场就年岁已高，在道观中沉溺修道炼丹，最后死于金丹中毒，书中惜春母亲在她出生不久去世，她一直在荣国府贾母、王夫人身边长大。（注意：虽然贾家四姐妹名字相仿，但贾元春、贾迎春、贾探春是荣国府的人，而贾惜春是宁国府的人。）', '2018-07-21 20:04:32');
INSERT INTO `t_emp` VALUES ('37', '尤氏', 'F', 'youshi@kaisn.com', '2', '4', '尤氏，别名大奶奶、珍大奶奶、珍哥儿媳妇，是曹雪芹小说《红楼梦》中的人物，贾珍的继室。虽为宁国府当家奶奶，但并无实权，素日只是顺从贾珍。她没什么才干，也没口齿。尤氏继母为尤老娘，有两个没血缘关系的妹妹尤二姐和尤三姐。', '2018-07-21 20:05:19');
INSERT INTO `t_emp` VALUES ('38', '贾蓉', 'M', 'jiarong@kaisn.com', '2', '11', '贾蓉，虚拟人物，出自中国古典小说《红楼梦》。宁国府贾珍之子，冷子兴演说荣国府时提到他十六岁。出场时十七八岁，是一个面目清秀，身材俊俏，轻裘宝带，美服华冠的男子。', '2018-07-21 20:06:15');
INSERT INTO `t_emp` VALUES ('39', '秦可卿', 'F', 'qinkeqin@kaisn.com', '2', '4', '中国古典小说《红楼梦》中的人物，金陵十二钗之一。贾蓉之妻，秦业的养女。她还有个无血缘关系的弟弟秦钟。来自仙界清净女儿之境，是太虚幻境之主警幻仙子的妹妹，乳名兼美，表字可卿。\r\n她长得袅娜纤巧，性格风流，行事温柔和平，被贾母赞为重孙媳中第一得意之人；而在警幻仙界，她是警幻仙姑的妹妹、\r\n她鲜艳妩媚有似宝钗，风流袅娜，则又如黛玉。原是个钟情的首坐，在警幻仙子座下管的是风情月债。在贾宝玉梦游太虚幻境时，被警幻仙姑许配给贾宝玉教他云雨之情。', '2018-07-21 20:07:02');
INSERT INTO `t_emp` VALUES ('40', '贾源', 'M', 'jiayuan@kaisn.com', '1', '11', '贾源是著名清代文学家曹雪芹在其巨著《红楼梦》中创建的文学形象。他是宁国公贾演的胞弟，被朝廷封为荣国公。他是宁国公贾演的胞弟，贾政、贾赦的祖父，贾宝玉的曾祖父。贾源被朝廷封为荣国公，死后由长子贾代善继承其爵位。', '2018-07-21 20:09:00');
INSERT INTO `t_emp` VALUES ('41', '贾代善', 'M', 'jiadaishan@kaisn.com', '1', '11', '荣国公贾源是宁国公贾演的弟弟，贾源死后他的儿子贾代善袭了他的爵位。贾代善的夫人就是贾母（又称史太君）。 贾代善生了两个儿子多个女儿，其中有姓名的分别是：贾赦、贾政和贾敏。 贾代善死后大儿子贾赦（妻：邢夫人）袭了爵位。贾赦有二子一女，即贾琏（妻：王熙凤，女：巧姐）、迎春和贾琮。', '2018-07-21 20:09:40');
INSERT INTO `t_emp` VALUES ('42', '贾母', 'F', 'jiamu@kaisn.com', '1', '4', '贾母，又称史太君，也被人们称为“老祖宗”。中国古典小说《红楼梦》中的主要角色之一，娘家姓史，也是四大家族之一。贾母是贾府的最高权位者。她是贾宝玉的祖母，也是林黛玉的外祖母，史湘云是其娘家兄弟的孙女。她的一生是贵族的代表人物，享尽一生荣华富贵。', '2018-07-21 20:10:28');
INSERT INTO `t_emp` VALUES ('43', '贾赦', 'M', 'jiashe@kaisn.com', '1', '11', '贾赦，虚拟人物，出自中国古典小说《红楼梦》，字恩侯，荣国公之孙，贾代善、贾母之长子，邢夫人的丈夫，贾琏、迎春的父亲，是个无耻之徒，他承袭了一等将军之职爵位。', '2018-07-21 20:11:01');
INSERT INTO `t_emp` VALUES ('44', '邢夫人', 'F', 'xingfuren@kaisn.com', '1', '4', '邢夫人，中国古典小说《红楼梦》中的人物。贾赦之妻子，无儿无女，地位尴尬，比不上妯娌王夫人。\r\n她禀性愚犟，只知奉承贾赦，家中小老婆一堆 ，一应大小事务，俱由贾赦摆布。出入银钱，一经她手，便克扣异常，婪取财货。儿女奴仆，一人不靠，一言不听，故甚不得人心。', '2018-07-21 20:11:41');
INSERT INTO `t_emp` VALUES ('45', '贾琏', 'M', 'jialian@kaisn.com', '1', '11', '贾琏，别名琏二爷，《红楼梦》中的人物，贾赦的长子。继母邢夫人无子女，异母妹妹迎春,异母弟弟贾琮，妻子王熙凤，女儿巧姐。他捐了个同知的官位，不喜欢读书谋求官位。住在荣国府叔叔贾政家中，和妻子王熙凤帮着料理家务，在贾府一众男子中算是第一得力干练之人。他最大的缺点是好色，但并不像贾珍、薛蟠等那样强人所难。女儿巧姐出天花，按迷信要夫妻分房，他一离开王熙凤就找“多姑娘” 鬼混。', '2018-07-21 20:12:15');
INSERT INTO `t_emp` VALUES ('46', '王熙凤', 'F', 'wangxifeng@kaisn.com', '1', '4', '王熙凤是中国古典小说《红楼梦》中人物，贾琏的妻子，王夫人的内侄女，贾府通称凤姐、琏二奶奶，金陵十二钗之一。她长着一双丹凤三角眼、两弯柳叶吊梢眉，身量苗条，体格风骚。在贾府掌握实权，为人心狠手辣，做事决绝，最后病逝。', '2018-07-21 20:12:50');
INSERT INTO `t_emp` VALUES ('47', '平儿', 'F', 'pinger@kaisn.com', '1', '4', '平儿，中国古典小说《红楼梦》中的人物，王熙凤的陪嫁丫头，贾琏的通房大丫头。她是个极聪明、极清俊的女孩儿。虽是凤姐的心腹，要帮着凤姐料理事务，但她为人很好，心地善良，常背着王熙凤做些好事。\r\n在高鹗续写的后40回中，王熙凤死后，王仁和贾环等要把巧姐卖给藩王作使女，是平儿陪伴巧姐逃出大观园。贾琏把平儿扶了正。', '2018-07-21 20:13:21');
INSERT INTO `t_emp` VALUES ('48', '尤二姐', 'F', 'youerjie@kansn.com', '1', '4', '尤二姐，《红楼梦》中的人物， 贾琏的二房，是贾珍夫人尤氏的继母带来的女儿。尤二姐模样标致，温柔和顺。贾珍馋涎妻妹的美貌，对尤二姐无微不至，当他玩腻后，就把她让给了贾琏。贾琏因惧怕王熙凤的淫威，只得偷偷娶尤二姐为二房，并把她安置在荣国府外，但不久被王熙凤发现，在她的借刀杀人计谋下，尤二姐备受折磨，当胎儿被庸医打下后，她绝望地吞金自尽。', '2018-07-21 20:13:51');
INSERT INTO `t_emp` VALUES ('49', '秋桐', 'F', 'qiutong@kansn.co', '1', '4', '秋桐，《红楼梦》人物，原为贾赦的丫环。贾琏偷娶尤二姐后，出门为父亲贾赦办事，事情办的很出色，贾赦十分欢喜，夸他能干，把秋桐赏他为妾。秋桐自以为揣摩到了凤姐的心思，加上自己是贾赦所赐，无人敢冒犯她。\r\n王熙凤曾借她用“借剑杀人”之计，把尤二姐迫害而死。', '2018-07-21 20:14:26');
INSERT INTO `t_emp` VALUES ('50', '贾迎春', 'M', 'jiayinchun@kansn.com', '1', '11', '贾迎春是中国古典小说《红楼梦》中的登场的虚拟人物，金陵十二钗之一，贾宝玉的堂姐。\r\n出身尚存争议，常见版本原文：“二小姐乃赦老爹之妾所出，名迎春，”有版本则说是贾赦前妻所出、贾政之妻所出、贾政所收养女等等。红学界普遍认为是曹雪芹多次删改未定稿而造成的疏误。\r\n迎春老实无能，懦弱怕事，有“二木头”的诨名。她不但作诗猜谜不如姐妹们，在处世为人上，也只知退让，任人欺侮。她的攒珠累丝金凤首饰被下人拿去赌钱（累金凤事件），她不追究。绣橘、探春、平儿设法要替她追回并惩处下人她却说：“宁可没有了，又何必生气。”她父亲贾赦欠了孙家五千两银子还不出，就把她嫁给孙家，实际上是拿她抵债。出嫁后不久（一年后），她就被孙绍祖虐待而死（“一载赴黄梁”），预示着荣国府已经开始逐步走向衰败。', '2018-07-21 20:15:19');
INSERT INTO `t_emp` VALUES ('51', '贾巧儿', 'F', 'jiaqiaoer@kaisn.com', '1', '4', '贾巧儿，王熙凤和贾琏之女。在“金陵十二钗”中排第九，年纪最小的一位。依俺的愚见，其实，她被安排在书中并上了十二正册恐怕也只是曹公用来烘托王熙凤这个人物罢了，从另一方面看，也是将她的遭遇从侧面来说明贾府没落后是怎样的一种形情，凸显了贾内部的种种矛盾。', '2018-07-21 20:16:37');
INSERT INTO `t_emp` VALUES ('52', '贾政', 'M', 'jiazheng@kaisn.com', '1', '11', '贾政，字存周，是曹雪芹著作《红楼梦》中的人物，荣国府二老爷，贾母和贾代善所生的次子，贾宝玉的父亲，林黛玉的舅舅，薛宝钗的姨父。\r\n他是除贾母外荣国府的最高掌权者，但同贾母一样不常管理府中大小俗务，每日只看书著棋，同一众清客闲聊，是名副其实的甩手掌柜，他并不喜好繁华奢侈的生活，在游览大观园时亦有过归农隐逸之意。\r\n他自幼好读书，但并不是天生的方正呆板，出仕前，他“也是个诗酒放诞之人”，但“一切为的是光宗耀祖”，因此重视读书上进，归于正途。', '2018-07-21 20:17:07');
INSERT INTO `t_emp` VALUES ('53', '王夫人', 'F', 'wangfuren@kaisn.com', '1', '4', '王夫人是在中国古典小说《红楼梦》中登场的虚拟人物，她是故事主角贾宝玉和十二钗之一的皇妃贾元春之母，贾政之妻，是荣国府掌权管事的家长之一。她性格沉闷守旧，贾母形容她老实。', '2018-07-21 20:18:13');
INSERT INTO `t_emp` VALUES ('54', '赵姨娘', 'F', 'zhaoyiniang@kaisn.com', '1', '4', '赵姨娘，古典小说《红楼梦》中的角色。她是荣国府二老爷贾政之妾，环三爷贾环和三姑娘贾探春之生母。赵姨娘是家生女儿出生，所谓家生女儿，就是小厮与放出去的丫鬟结合生下的下一代。\r\n也许，赵姨娘年轻时候是漂亮的，看探春的容貌大概可以略知一二。于是，赵姨娘成了贾政的妾，这是她幸运的一步，却也是不幸的开始。', '2018-07-21 20:18:41');
INSERT INTO `t_emp` VALUES ('55', '贾珠', 'M', 'jiazhu@kaisn.com', '1', '11', '贾珠，贾政嫡长子，与贾元春、贾宝玉均为王夫人所生，娶妻李纨，育子贾兰，在《红楼梦》中是作为已故人物。\r\n《红楼梦》第二回中，作者主要通过冷子兴之口对其作了很简要的介绍：“十四岁进学，不到二十岁就娶了妻生了子，一病死了。', '2018-07-21 20:19:06');
INSERT INTO `t_emp` VALUES ('56', '李纨', 'F', 'liwan@kaisn.com', '1', '4', '古典名著《红楼梦》中人物，金陵十二钗之一，字宫裁。她是荣国府长孙贾珠之妻。贾珠夭亡，幸存一子，取名贾兰。亦系金陵名宦之女，父名李守中，曾为国子监祭酒。李纨青春守寡，心如“槁木死灰”，是封建淑女，是标准的节妇，是妇德妇功的化身。但她进入大观园后，恢复了青春朝气，不但带领诗社兴旺发达，而且把大观园治理成青春女儿的净土和乐园。', '2018-07-21 20:19:52');
INSERT INTO `t_emp` VALUES ('57', '贾宝玉', 'M', 'jiabaoyu@kaisn.com', '1', '11', '贾宝玉，中国古典名著《红楼梦》中的男主角。前世真身为赤霞宫神瑛侍者，荣国府贾政与王夫人所生的次子。因衔通灵宝玉而诞，系贾府玉字辈嫡孙，故名贾宝玉，贾府通称宝二爷。他是大观园女儿国中唯一的男性居民。 [1] \r\n贾宝玉自幼深受贾母疼爱，游于温柔富贵乡，专爱作养脂粉，亲敬家里姐妹和丫鬟；他与林黛玉青梅竹马，互为知己，发展成一段世间少有的纯洁爱情；他重情不重礼，结交了秦钟、柳湘莲、北静王等有情男子；他喜欢诗词曲赋之类性情文学，厌恶四书和八股文，批判程朱理学，把那些追逐科举考试、仕途经济的封建文人叫做“禄蠹”。可是到头来“瞬息间则又乐极悲生，人非物换” [2]  。他所爱的清净女儿们死的死，散的散；自身又在家族安排下糊里糊涂与薛宝钗结婚，致使林黛玉泪尽而逝；再经抄家之痛，越发唬得他疯疯傻傻。\r\n为了报答天恩祖德，也为了尽快了却尘缘，他以高魁贵子重振家业。最后情极而毒，悬崖撒手，跟随一僧一道出走，回到青埂峰，“究竟是到头一梦，万境归空” [2]  。\r\n贾宝玉形象带有曹雪芹自传的色彩，但其本质上属于艺术虚构，是作者有意识塑造的集意淫、补天济世、正邪两赋三大美德于一身的典型形象，在世界文学史上极具创新性。', '2018-07-21 20:20:25');
INSERT INTO `t_emp` VALUES ('58', '薛宝钗', 'F', 'xuebaocai@kaisn.com', '1', '4', '薛宝钗，是曹雪芹著长篇章回体小说《红楼梦》中的女主角之一，与林黛玉并列为金陵十二钗之首，贾宝玉的从母姊（姨姊）、妻子。\r\n她容貌丰美，举止娴雅，博学多才，因此受到贾府上下一致好评。\r\n父亲（薛姨爸）早亡，有母亲（薛姨妈）和哥哥（薛蟠）。宝钗进京后与母亲薛姨妈、哥哥薛蟠暂住于贾府的梨香院，后迁居与东北上一处幽静的房所。因红楼梦八十回后失传，故据推测，林黛玉病死后，贾宝玉与薛宝钗成婚，但最终未与之白头偕老，贾宝玉没多久便看破红尘出家为僧。', '2018-07-21 20:20:52');
INSERT INTO `t_emp` VALUES ('59', '贾探春', 'F', 'jiatanchun@kaisn.com', '1', '4', '贾探春，曹雪芹所著《红楼梦》中人物，金陵十二钗之一，荣国府贾政与奴婢出身的妾侍赵姨娘所生的女儿，贾宝玉同父异母的妹妹，贾府通称三姑娘，宝玉及三春均在贾母身边抚养长大，所受教育极好，与嫡姐元春无异，林黛玉进贾府后搬到王夫人住处，元春省亲后住大观园。她精明能干，富有心机，能决断，有“玫瑰花”之诨名，连王夫人与凤姐都忌惮她几分，抄检大观园时她扇了王善保家的一巴掌；她工诗善书，趣味高雅，曾发起建立海棠诗社，是大观园中的一位大才女；她关心家国大事，有经世致用之才，曾奉王夫人之命代凤姐理家，为了捧太太重用的奴婢袭人而打压赵姨娘，造成明明是奴婢家眷不该有丧礼费却高过身为姨太大赵姨娘的家眷，并主持大观园改革，是一位雄才伟略的政治家、改革家。', '2018-07-21 20:21:24');
INSERT INTO `t_emp` VALUES ('60', '贾环', 'M', 'jiahuan@kaisn.com', '1', '11', '贾环，《红楼梦》中人物，同贾宝玉为同父异母的弟弟，其母为贾政之妾赵姨娘。贾环颇为顽劣，正月里赶围棋玩耍时，对薛宝钗的丫头莺儿撒泼耍赖，进而在母亲前撒谎污蔑宝玉、莺儿。贾环十分忌恨其嫡兄贾宝玉，多次陷害宝玉。故意拨翻烛台 ，烫伤宝玉。金钏跳井事件后，诬陷宝玉，使得宝玉遭受贾政毒打。', '2018-07-21 20:21:45');
INSERT INTO `t_emp` VALUES ('61', '贾兰', 'M', 'jialan@kaisn.com', '1', '11', '贾兰，《红楼梦》中贾珠与李纨之子。根据高鹗整理的续本：贾兰成年后考中第一百三十名举人。', '2018-07-21 20:22:14');
INSERT INTO `t_emp` VALUES ('62', '史鼐', 'M', 'shinai@kaisn.com', '3', '11', '史鼐：《红楼梦》中的男性人物，世袭保龄侯、尚书令史公之次孙(也就是史公的嫡长子的次子)，忠靖侯史鼎之兄，史湘云之叔，贾母之侄。首见于第25回《魇魔法姊弟逢五鬼 红楼梦通灵遇双真》。', '2018-07-21 20:22:34');
INSERT INTO `t_emp` VALUES ('63', '史鼎', 'M', 'shiding@kaisn.com', '3', '11', '史鼎：《红楼梦》中人物。忠靖侯。尚书令史公的第三孙，也就是史公的嫡长子的第三子。为史鼐之弟、史湘云之三叔、贾母之侄。', '2018-07-21 20:22:56');
INSERT INTO `t_emp` VALUES ('64', '王子腾', 'M', 'wangziteng@kaisn.com', '4', '11', '王子腾，《红楼梦》中人物。首见第三回。都太尉统制县伯王公之后裔，王夫人、薛姨妈、王子胜之兄。初任京营节度使，后擢九省统制，奉旨查边，旋升九省都检点。鲍二家的自缢后，阻其娘家亲戚申诉，并左右都察院审理张华一案的，都是王熙凤倚王子腾之势所为。而他又因贾雨村私断金陵命案便累上保本、引荐雨村进京。', '2018-07-21 20:23:24');
INSERT INTO `t_emp` VALUES ('65', '王仁', 'M', 'wanren@kaisn.com', '4', '11', '王仁，居住于古代百济国的汉人，是传说应神天皇时中从百济前往日本，在当地传达了汉字和儒教的人物。有些学者也怀疑其人是否真实存在。日本书纪上的写法是王仁，古事记上的写法则是和迩吉师。', '2018-07-21 20:23:47');
INSERT INTO `t_emp` VALUES ('66', '薛姨妈', 'F', 'xueyima@kaisn.com', '5', '4', '薛姨妈，古典小说《红楼梦》中的人物，薛蟠和薛宝钗之母，王夫人之妹。丧夫后携带一双儿女寄居贾府的梨香院，谐音“离乡怨”，后因元妃省亲，贾府采买的小戏子们入住院中学戏，薛姨妈等又迁住于园中东北上一处幽静住所。\r\n她以“慈”著称，由于举止言行得体，贾母常愿与她对坐聊天。她十分溺爱自己的子女，对举目无亲的林黛玉也尤为怜爱，认其作干女儿。薛姨妈尤其怜惜薛蟠独根孤种，未免溺爱纵容，遂至老大无成，任薛蟠在外为所欲为。', '2018-07-21 20:24:17');
INSERT INTO `t_emp` VALUES ('67', '薛蟠', 'M', 'xuepan@kaisn.com', '5', '11', '薛蟠，古典小说《红楼梦》中的人物，字文龙（一作“文起”），外号“呆霸王”，薛姨妈之子，薛宝钗之兄。因幼年丧父，寡母又纵容溺爱，终日唯有斗鸡走马，游山玩水。', '2018-07-21 20:24:44');
INSERT INTO `t_emp` VALUES ('68', '夏金桂', 'F', 'xiajingui@kaisn.com', '5', '4', '夏金桂是在《红楼梦》中登场的虚拟角色，由杨晓玲饰演。薛蟠的妻子。\r\n她家和薛家一样，同在户部挂名行商，也就是皇商，也是数一数二的大门户。家中只有一个寡母带着夏金桂，与薛蟠一样，被寡母纵容教养长大，她爱自己尊若菩萨，窥他人秽如粪土，外具花柳之姿，内秉风雷之性，嫁到薛家，使得薛家上下吃尽了苦头。', '2018-07-21 20:25:11');
INSERT INTO `t_emp` VALUES ('69', '香菱', 'F', 'xiangling@kaisn.com', '5', '4', '甄英莲（“真应怜”），是在古典小说《红楼梦》中的人物，金陵十二钗副册女儿，贾府通称香菱。甄英莲原籍姑苏，甄士隐独女，眉心有米粒大小的一点胭脂记。是小说中第一个登场的女性人物。', '2018-07-21 19:58:02');
INSERT INTO `t_emp` VALUES ('70', '宝蟾', 'F', 'baichan@kaisn.com', '5', '4', '宝蟾，古典小说《红楼梦 [1]  》中的人物，夏金桂的陪房丫头。长有三分姿色，举止轻浮。她与夏金桂这一对主仆是曹雪芹最后推出的一组人物，两个可悲可怜可恨的女人。\r\n宝蟾初见于第80回，其后又见于第90、91回，第103回结束。宝蟾的名字与夏金桂颇有关合，桂蟾相合则是月亮的别称——蟾宫有桂，桂宫有蟾，形影相随', '2018-07-21 20:26:17');
INSERT INTO `t_emp` VALUES ('71', '薛蝌', 'M', 'xueke@kaisn.com', '5', '11', '薛蝌是曹雪芹小说《红楼梦》中的人物——皇商之子，薛姨妈的侄儿，薛宝琴的胞兄，薛蟠和薛宝钗的堂弟。', '2018-07-21 20:26:42');
INSERT INTO `t_emp` VALUES ('72', '邢岫烟', 'F', 'xingxiuyan@kaisn.com', '5', '4', '邢岫烟是《红楼梦》中邢忠夫妇的女儿，邢夫人的侄女。她家道贫寒，一家人前来投奔邢夫人，就在大观园迎春的住处紫菱洲住下。邢夫人对邢岫烟并不真心疼爱，只不过为了脸面之情。邢夫人甚至要求邢岫烟把每月二两银子的月钱省下一两来给她自己的父母，使得邢岫烟只得典当衣服来维持她在大观园的开支。邢岫烟生得端雅稳重，知书达礼，被薛姨妈看中，央求贾母作媒说与薛蝌，在程高伪续的通行本中后嫁给薛蝌。', '2018-07-21 20:27:07');
INSERT INTO `t_emp` VALUES ('73', '贾元春', 'F', 'jiayuanchun@kaisn.com', '7', '4', '贾元春是中国古典小说《红楼梦》中的人物。金陵十二钗之一，贾政与王夫人所生的嫡长女，贾珠的亲妹妹，贾宝玉的亲姐姐，贾家四姐妹之首。贾元春比贾珠小一两岁，比宝玉大十一二岁，贾府通称娘娘。\r\n贾元春因生于正月初一而起名元春。元春十几岁时便已入宫做女史，23-24岁时加封贤德妃。为了迎接元春省亲，贾府建造了大观园。元春24-25岁回娘家省亲，热闹欢腾，同时又表现出她在深宫高处不胜寒的辛苦。元春的命运关乎贾府兴衰，秦可卿之死标志着贾府末世来临，元春晋封贵妃则令贾府重现生机，她也是四大家族最大的支柱。高鹗续书的后四十回她与王子腾先后暴卒，贾府失去了靠山，很快就获罪抄家。（根据第五回的判词，“虎兕相逢大梦归”，还有脂砚斋在元春点的一出《乞巧》中批：‘《长生殿》中伏元妃之死’可以判断，贾元春应该是怀孕后牺牲于政治斗争。注：《长生殿》中杨玉环与唐玄宗，杨玉环死于太子政变。）', '2018-07-21 20:27:41');
INSERT INTO `t_emp` VALUES ('74', '史湘云', 'F', 'shixiangyun@kaisn.com', '8', '4', '史湘云是中国古典名著《红楼梦》中的人物，金陵十二钗之一，金陵省贾、史、王、薛四大家族史家的千金，父母在她还是襁褓中婴儿的时候就已经亡故，由叔叔婶婶养育。她是贾母的内侄孙女，贾府通称史大姑娘。\r\n史湘云是一个富有浪漫色彩、令人喜爱的豪放女性。但她毕竟是薄命司中的女儿，自幼父母双亡，由于史家经济拮据，也没有过上贵族小姐娇生惯养的生活；经史湘云的判词中推测，她最后嫁了一位才貌俱佳的郎君，但夫婿很快亡故，过着拮据的孤寡生活。', '2018-07-21 20:28:03');
INSERT INTO `t_emp` VALUES ('75', '薛宝琴', 'F', 'xuebaoqing@kaisn.com', '8', '4', '薛宝琴，《红楼梦》中皇商之女，小时跟父亲跑过不少地方。她是薛姨妈的侄女，薛蝌的胞妹，薛蟠，薛宝钗的堂妹。她长得十分美貌，贾母甚是喜爱，夸她比画上的还好看，曾欲把她说给贾宝玉为妻。王夫人也认她为干女儿。她自幼读书识字，本性聪敏，在大观园里曾作《怀古绝句十首》。后嫁都中梅翰林之子。她是一位近乎完美的人。她的美艳与纯真和邢岫烟的内敛与清高、李纹、李绮的超脱与淡然截然不同，十分耀眼。主要成就为作怀古诗10首。', '2018-07-21 20:28:24');
INSERT INTO `t_emp` VALUES ('76', '尤三姐', 'F', 'yousanjie@kaisn.com', '8', '4', '尤三姐，中国古典文学名著《红楼梦》中人物，尤氏继母带来的女儿，尤二姐的妹妹，亦称作尤小妹。尤三姐模样儿风流标致，她又偏爱打扮得出色，自有一种万人不及的风情体态。\r\n贾珍、贾蓉俩父子垂涎尤氏姐妹的美貌，与她们厮混起来。但尤三姐决定不再身陷囹圄，她用泼辣作为武器，让父子二人不敢在轻看她。她看中柳湘莲后，就托付姐夫贾琏与之定亲。但因柳湘莲认为宁国府污浊不堪，认为尤三姐也是个不干净之人，要索回定礼，刚烈的尤三姐在奉还定礼时自杀。另有同名电影（京剧）与京剧剧目。', '2018-07-21 20:28:50');
INSERT INTO `t_emp` VALUES ('77', '孙绍祖', 'M', 'sunsaozu@kaisn.com', '8', '11', '孙绍祖，迎春丈夫孙绍祖在家境困难时曾经拜倒在贾府门下，乞求帮助。后来，孙绍祖在京袭了官职，又“在兵部候缺题升”，贾家衰败后，孙绍祖向他逼债，任意践踏迎春。\r\n迎春判词有“迎春遭遇中山狼”一幅，画中的孙绍祖，高壮如异兽，十分可怕。“中山狼”之形态毕出也。在这桩弱女悍夫的不幸婚姻的室内细节，文字没有写到之处，似乎都可从画中窥出。', '2018-07-21 20:29:13');
INSERT INTO `t_emp` VALUES ('78', '卫若兰', 'M', 'weiruolan@kaisn.com', '8', '11', '卫若兰是《红楼梦》中的人物，在《红楼梦》中只出现过很少的几次，仅在秦氏出殡时出现了一次，书中点明他的身份是“王孙公子”。他之所以成为引人注目的人物，是由于脂批的特意提及。', '2018-07-21 20:29:37');
INSERT INTO `t_emp` VALUES ('79', '梅翰林之子', 'M', 'meihanlinzhizi@kaisn.com', '8', '11', '本是书香继世之家．只是如今这薛公子幼年丧父，寡母又怜他是个独根孤种，未免溺爱纵容，遂至老大无成，且家中有百万之富，现领着【内帑钱粮，采办杂料．】【不过赖祖父之旧情分，户部挂虚名，支领钱粮】', '2018-07-21 20:30:48');
INSERT INTO `t_emp` VALUES ('80', '贾敏', 'F', 'jiamin@kaisn.com', '6', '4', '贾敏是清代小说《红楼梦》中的人物，她是贾代善与史太君之女，也是林黛玉之母。谈吐不凡，在《红楼梦》这本名著中作为已故人物出场。贾母最疼爱的小女儿，嫁与巡盐御史林如海为妻。先育有一子，早夭，后生下林黛玉。早逝。', '2018-07-21 20:31:16');
INSERT INTO `t_emp` VALUES ('81', '林如海', 'M', 'linruhai@kaisn.com', '6', '11', '林如海，中国古典小说《红楼梦》中的人物，姑苏林家的子孙，娶妻贾敏（已先于林如海病故），育有一子，三岁时没了，仅有一女林黛玉。', '2018-07-21 20:31:40');
INSERT INTO `t_emp` VALUES ('82', '林黛玉', 'F', 'lindaiyu@kaisn.com', '6', '4', '林黛玉，中国古典名著《红楼梦》的女主角。\r\n金陵十二钗正册双首之一，西方灵河岸绛珠仙草转世身魂，荣府么女贾敏与巡盐御史林如海之独生女，贾母的外孙女，贾宝玉的姑表妹、恋人、知己，贾府通称林姑娘。 [1] \r\n她生得倾城倾国容貌，兼有旷世诗才，是世界文学作品中最富灵气的经典女性形象。从小聪明清秀，父母对她爱如珍宝。5岁上学，6、7岁母亲早亡，10岁师从贾雨村启蒙。\r\n外祖母贾史氏疼爱幺女贾敏，爱屋及乌疼爱黛玉，10岁接到身边抚养教育，寝食起居，一如嫡孙贾宝玉。与11岁的贾宝玉同住同吃，吃穿用度都是贾母打点，自视地位在三春之上，实则只是隔一代近亲，因被王夫人的仆人最后一个送宫花而很不愉快。11岁时又死了父亲，从此常住贾府，养成了孤标傲世的性格。12岁时，贾元春省亲后，林黛玉入住潇湘馆，在大观园诗社里别号潇湘妃子，作诗直抒性灵。\r\n林黛玉与贾宝玉青春年少，有共同的理想志趣和叛逆精神而慢慢发展成爱情。绛珠还泪的神话赋予了林黛玉迷人的诗人气质，为宝黛爱情注入了带有奇幻元素的罗曼蒂克色彩，同时又定下了悲剧基调。\r\n林黛玉与薛宝钗在太虚幻境才女榜上并列第一，二人既存在人性上的德才之争，思想上的忠叛之争，婚姻上的金木之争，又因同属正邪两赋的禀性而惺惺相惜 [2]  。无奈在封建礼教压迫下，林黛玉受尽“风刀霜剑严相逼”之苦，最后于贾宝玉、薛宝钗大婚之夜泪尽而逝。', '2018-07-21 20:32:20');
INSERT INTO `t_emp` VALUES ('83', '花袭人', 'F', 'xiren@kaisn.com', '1', '4', '袭人，中国古典小说《红楼梦》中人物，花姓，金陵十二钗又副册第二位，宝玉房里四个大丫鬟之首。袭人原名珍珠（程乙本作“蕊珠”），从小因家贫被父母卖入贾府为婢，原是跟着贾母，起先服侍史湘云几年，贾母见喜袭人心地纯良，恪尽职守，便命她服侍宝玉', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('84', '晴雯', 'F', 'qingwen@kaisn.com', '1', '4', '晴雯，中国古典小说《红楼梦》中人物，金陵十二钗又副册之首，贾宝玉房里的四个大丫鬟之一，虽是丫鬟，但在宝玉房里过得千金小姐的生活。红学中普遍评价她有林黛玉之风。她长得风流灵巧，眉眼儿有点像林黛玉，口齿伶俐，针线活尤好，曾为宝玉病补雀金裘。', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('85', '麝月', 'F', 'sheyue@kaisn.com', '1', '4', '麝月，《红楼梦》中人物，是主人公贾宝玉身边一等丫鬟（丫鬟也分一等二等，不细论）。按照第五回众丫鬟的排序“袭人、晴雯、麝月、秋纹”，她并不突出。', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('86', '鸳鸯', 'F', 'yuanyang@kaisn.com', '1', '4', '红楼人物，鸳鸯在《红楼梦》一书中，是贾母的大丫头。家生奴，甚受信任。贾母平日倚之若左右手。贾母玩牌，她坐在旁边出主意；贾母摆宴，她入座充当令官。因为这个缘故，她在贾府的丫头中有很高的地位。但她自重自爱，从不以此自傲，仗势欺人，因此深得上下各色人等的好感和尊重。她长得蜂腰削肩，鸭蛋脸，乌油头发，高高的鼻子，两边腮上微微的几点雀斑。', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('87', '小红', 'F', 'xiaohong@kaisn.com', '1', '4', '又叫红玉，她姓林，那么因为她的名字这个“玉”呢，重了宝玉和黛玉的名，要避讳，所以就叫小红。红楼梦第二十七回中宝钗偷听到小红的私密事便嫁祸黛玉的情节。', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('88', '金钏', 'F', 'jinchuan@kaisn.com', '1', '4', '金钏的正式出场是刘姥姥去后，周瑞家的回王夫人话找到梨香院时，与香菱（此时是英莲之名作古的开端）一起出现。此前收养林黛玉一段，拜见二舅舅的时候，曾有见一穿红绫袄，青缎掐牙背心的丫鬟，是金是玉、是霞是云并未做介绍。出场便如画如描“向内努嘴”，一副虑事的样子，又多少有些顽皮。', '2018-07-21 17:45:08');
INSERT INTO `t_emp` VALUES ('89', '紫鹃', 'F', 'zijuan@kaisn.com', '1', '4', '原来是贾母身边的一个二等丫头，名叫“鹦哥”，后来林黛玉进贾府以后，贾母让鹦哥去服侍黛玉，并改名为紫鹃。后来就成了黛玉身边十几个女仆当中，地位最高的一个，“比他扬州带来的（雪雁）还要好十倍”，成为与鸳鸯、平儿等人地位相当的“首席大丫头”。情慧', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('90', '莺儿', 'F', 'yinger@kaisn.com', '1', '4', '莺儿，《红楼梦》中薛宝钗的丫头。原名黄金莺，因薛宝钗嫌拗口，改叫莺儿。她甚是乖巧，薛宝钗在观看通灵宝玉，念著玉上所镌之文“莫失莫忘，仙寿恒昌”时，她马上想到这和小姐项圈上的两句话是一对儿。她手特巧，擅长打络子、编花篮等，还颇懂色彩的搭配。情络', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('91', '司棋', 'F', 'siqi@kaisn.com', '1', '4', '中国古典小说《红楼梦》中的人物。贾迎春的丫头。她身材高大丰壮，与做小厮的表哥潘又安相爱。有一次在园内幽会时，被鸳鸯无意撞见。潘又安害怕鸳鸯会说出此事，吓得连家也不敢回，逃走了。从第七十一回起，作者渐掀司棋命运的高潮。该回写鸳鸯无意撞破司棋与表哥的私情。', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('92', '玉钏', 'F', 'yuchuan@kaisn.com', '1', '4', '玉钏，与姐姐金钏同为王夫人房中丫头，她与姐姐感情很深，金钏被逼跳井自杀，她知道与贾宝玉有关，心中甚恨宝玉。后来，在王熙凤生日那天，宝玉带茗烟去水仙庵祭奠金钏，回来，告诉了玉钏，估计玉钏也就释怀了。', '2018-07-21 17:45:13');
INSERT INTO `t_emp` VALUES ('93', '茜雪', 'F', 'qianxue@kaisn.com', '1', '4', '茜雪是宝玉跟前的大丫头之一。第八回写到宝玉从梨香院吃酒回到绛芸轩，半醉中接过茜雪捧上的茶，吃了半碗，忽又想起早起的茶来，因问茜雪道：“早起沏了一碗枫露茶，我说过，那茶是三四次后才出色的，这会子怎么又沏了这个来？”茜雪道：“我原是留着的，那会子李奶奶来了，他要尝尝，就给他吃了。”', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('94', '柳五儿', 'F', 'liuwuer@kaisn.com', '1', '4', '柳五儿，厨役之女，因家中排第五，故名柳五儿。五儿生得与平儿、袭人、鸳鸯模样相类。她和芳官是好朋友，有一天她到怡红院去给芳官送茯苓霜，回来路上被林之孝家的发现，怀疑她是贼。王熙凤要把她打四十大板后，立刻交到庄子上，或卖或配人，幸亏平儿相助，软禁了一夜就放了。后成了宝玉的丫环，宝玉甚是喜欢，把她当作晴雯。', '2018-07-21 12:01:03');
INSERT INTO `t_emp` VALUES ('95', '北静王', 'M', 'beijingwang@kaisn.com', '7', '11', '北静王，曹雪芹《红楼梦》中的人物（首见第十四回），名水溶，年未弱冠，形容秀美，性情谦和。因祖上与贾府有世交之谊，故从未以异姓相见，更不以王位自居。\n秦可卿出丧，他特设路祭，在路旁高搭彩棚，设席张筵，和音奏乐，哀悼吊唁。又专请贾宝玉相见。他对通灵宝玉“称奇道异”了一番，夸奖宝玉果然如“宝”似“玉”，“真乃龙驹凤雏”，并把皇上亲赐之鹡鸰念珠一串赠与宝玉。贾宝玉素厌官僚权贵，但平日闻得北静王风流潇洒，不为官俗国体所缚，每思相会，所以相见之下，彼此都有惺惺相惜之意。', '2018-07-21 20:41:54');
INSERT INTO `t_emp` VALUES ('96', '瑞珠', 'F', 'ruizhu@kaisn.com', '2', '4', '秦可卿的丫环.秦可卿生前与公公贾珍关系暧昧，服侍秦可卿的瑞珠应该有所觉察，因此受到贾珍的威胁。秦可卿死后，瑞珠触柱而亡。', '2018-07-21 22:21:30');
INSERT INTO `t_emp` VALUES ('97', '娇杏', 'F', 'jiaoxing@kaisn.com', '9', '4', '娇杏是《红楼梦》中甄家的丫环，金陵十二钗副册。 “ 生得仪容不俗，眉目清秀，虽无十分姿色，却亦有动人之处。', '2018-07-21 22:24:36');
INSERT INTO `t_emp` VALUES ('98', '李绮', 'F', 'liyi@kaisn.com', '8', '4', '首见《红楼梦》第四十九回\r\n李绮　李纨之寡婶的女儿，嫁给了甄宝玉。', '2018-07-21 22:27:09');
INSERT INTO `t_emp` VALUES ('99', '李纹', 'F', 'liwen@kaisn.com', '8', '4', '李纨之寡婶的女儿， 首见《红楼梦》第四十九回\r\n李婶娘之女，李纨堂妹。晴雯赞她和李绮都是水葱儿般水灵的姑娘。随母在大观园住下后，她曾参加了几次诗社活动，惟未细表。后王夫人说她已许了人家。\r\n在第五十回中她作有一首<<咏红梅花>>诗，里头有两句是\"冻脸有痕皆是血，酸心无恨亦成灰\"，可见结局也是悲惨的。\r\n原来邢夫人之兄嫂带了女儿岫烟进京来投邢夫人的，可巧凤姐之兄王仁也正进京，两亲家一处打帮来了。走至半路泊船时，正遇见李纨之寡婶带着两个女儿──大名李纹，次名李绮──也上京。大家叙起来又是亲戚，因此三家一路同行。后有薛蟠之从弟薛蝌，因当年父亲在京时已将胞妹薛宝琴许配都中梅翰林之子为婚，正欲进京发嫁，闻得王仁进京，他也带了妹子随后赶来。所以今日会齐了来访投各人亲戚。', '2018-07-21 22:29:16');
INSERT INTO `t_emp` VALUES ('100', '赖大', 'M', 'laida@kaisn.com', '1', '11', '赖大：《红楼梦》的人物（首见第十六回），赖嬷嬷之子，赖尚荣之父。是“熬了两三辈子，好容易挣出”来的“家生子儿”，因为赖嬷嬷“年高服侍过”贾府的老主子，又得到贾母的“赏脸”，他才做了荣府大总管。', '2018-07-21 22:35:12');
INSERT INTO `t_emp` VALUES ('101', '林之孝', 'M', 'lizixiao@kaisn.com', '1', '11', '林之孝，《红楼梦》荣国府管家，负责管理银库账房的，为人处世十分低调。他不善言谈，女儿林红玉〈87版红楼梦中林之孝的女儿小红\r\n87版红楼梦中林之孝的女儿小红〉先是宝玉丫环，后成凤姐的丫环，林之孝曾劝贾琏不要将丫环彩霞许配给旺儿之子，但王熙凤说已经答应了，不能改。', '2018-07-21 22:38:08');
INSERT INTO `t_emp` VALUES ('102', '周瑞', 'M', 'zhourui@kaisn.com', '1', '11', '周瑞，《红楼梦》人物。冷子兴岳丈。荣府的男管家。名义上只管春秋两季地租，闲时带少爷们出门，其实暗地里还替凤姐等放帐收银。昔年争买田地时，曾得刘姥姥主婿狗儿相助，故刘姥姥初进荣国府便由其妻周瑞家的引见。后因干儿子何三打劫贾府案发，失宠被撵。', '2018-07-21 22:39:56');
INSERT INTO `t_emp` VALUES ('103', '来旺', 'M', 'laiwang@kaisn.com', '1', '11', '来旺（首见第十五回）荣国府男仆，是凤姐的陪房。他的媳妇（来旺家的）专替凤姐放账收债，是王熙凤得力的心腹。凤姐受贿为张家退婚，一说与他听，他就“心中俱已明白”，急忙连夜赶往长安县，“两日工夫俱已妥协”。\r\n新红楼的来旺\r\n新红楼的来旺\r\n凤姐欲“借剑”杀尤二姐，他除了“在外打听细事”，就是上堂与张华对词，直逼得张华牵扯出贾蓉等，为“酸凤姐大闹宁国府”（第六十八回）铺平了道路。但他并非一味蛮干之人，一旦凤姐命他“务将张华治死”，他就想到“人命关天，非同儿戏”（第六十九回），终蒙混了事。', '2018-07-21 22:41:49');
INSERT INTO `t_emp` VALUES ('104', '王善保', 'F', 'wangshanbao@kaisn.com', '1', '4', '王善保，《红楼梦》中的人物，十二家人之一。 十二家人分别是：赖大、焦大、王善保、周瑞、林之孝、乌进孝、包勇、吴贵、吴新登、邓好时、王柱儿、余信。 王善保家的是曹雪芹小说《红楼梦》中的人物——邢夫人的陪房，也是邢夫人的得力心腹人。', '2018-07-21 22:45:54');
INSERT INTO `t_emp` VALUES ('105', '吴新登', 'M', 'wuxindeng@kaisn.com', '1', '11', '吴新登：（首见曹雪芹《红楼梦》第八回）荣府银库房总领。他是贾府几个头面男家人之一，也参与兴建大观园的安插摆布，每年还设家宴请贾母等吃年酒。脂砚斋评他名字“盖云无星戥也”（甲戌本）。银库房的总领既是“无星戥”，其中流弊可想而知。', '2018-07-21 22:47:08');
INSERT INTO `t_emp` VALUES ('106', '赖升', 'M', 'laisheng@kaisn.com', '2', '11', '宁国府管家的名字,在《红楼梦》各版本中,有的作\"赖大\",有的作\"赖二\"。此名后来又演变为\"来昇\"、\"来升\"\"、来陞\"或\"赖昇\"\"、赖升\"、\"赖陞\"。', '2018-07-21 22:50:33');
INSERT INTO `t_emp` VALUES ('107', '焦大', 'M', 'jiaoda@kaisn.com', '2', '11', '焦大，中国古典小说《红楼梦》中人物。宁国府的老仆。从小跟宁国公贾演出过三四回兵，曾从死人堆里把奄奄一息的主子背出来。没有饭吃，他饿著肚子去偷东西给主子吃，没有水喝，他自己喝马尿，把得来的半碗水给主子喝。由于以往的功劳情分，宁府的主子们对他另眼相看，不大难为他。他对宁国府后代糜烂的生活深恶痛绝，也只有他在喝醉酒后敢大骂他们：“每日偷狗戏鸡，爬灰的爬灰，养小叔子的养小叔子”，吓得众小厮魂飞魄丧，把他捆起来，用土和马粪满满填了他一嘴。', '2018-07-21 22:51:43');
INSERT INTO `t_emp` VALUES ('108', '秦钟', 'M', 'qinzhong@kaisn.com', '8', '11', '秦钟，《红楼梦》中人物，表字鲸卿。秦业的儿子，秦可卿的弟弟。他生得眉清目秀，粉面朱唇，身材俊俏，举止风流，怯怯羞羞的有些女儿之态。', '2018-07-21 22:56:25');
INSERT INTO `t_emp` VALUES ('109', '柳湘莲', 'M', 'liuxianglian@kaisn.com', '8', '11', '柳湘莲，《红楼梦》中人物，又称冷面二郎，原系世家子弟。他父母早丧，读书不成。性情豪爽，酷好耍枪舞剑，赌博吃酒，以至眠花宿柳，吹笛弹筝，无所不为。他生得又美，是一个业余的戏剧演员，最喜串戏，擅演生旦风月戏文，在书中和宝玉最合得来。\r\n有一次在赖大家赴宴，薛蟠酒后向他调情，被他骗至北门（今德胜门）外苇子坑打了个半死，事后，远走他乡。 后又在路上救了薛蟠，与薛蟠结为兄弟。足见其无邪之心。行事草率，不负责任。\r\n尤三姐说出她择柳湘莲为夫后，贾琏巧遇柳湘莲，遂定下婚事，柳湘莲赠“鸳鸯剑”为定礼。后湘莲自己疑\r\n柳湘莲\r\n柳湘莲\r\n惑，觉得事情蹊跷，误以为尤三姐是不干不净之人，要索回定礼，尤三姐在退还“鸳鸯剑”时用雌锋自尽，柳湘莲深为感动，大哭一场，掣出“鸳鸯剑”的雄锋，将万根烦恼丝一挥而尽，随跛足道士出家去了。', '2018-07-21 22:58:33');
INSERT INTO `t_emp` VALUES ('110', '贾雨村', 'M', 'jiayucun@kaisn.com', '8', '11', '贾雨村是在《红楼梦》中登场的虚拟人物，他是一个提纲挈领式的人物，以“假语村言”提醒读者，统率全文。 贾雨村，名化，字时飞，别号雨村，故为“假语村言”。原系湖州人氏，生于仕宦人家。但到他时，祖宗根基已尽，人口衰丧，只剩下他一人。他想进京求取功名，无奈囊内空空，只得暂寄姑苏城里葫芦庙中安身，每日卖文作字为生。后因甄士隐相助，他才有钱上路，考中进士，做了知府。不久因贪酷徇私被革职，受聘至林如海家任林黛玉启蒙教师。在贾政的极力帮助下，他又官复原职，但为官不正，乱判了一起“葫芦案”（为“糊涂案”），后来这一案件被世人所知，因为“因嫌纱帽小，致使锁枷杠”。', '2018-07-21 23:00:07');
INSERT INTO `t_emp` VALUES ('111', '甄士隐', 'M', 'zhenshiyin@kaisn.com', '8', '11', '甄士隐，古典小说《红楼梦》中的人物，姓甄，名费，谐音“废”，字士隐。“甄士隐”取意为“真事隐”。书中有“因曾历过一番梦幻之后，故将真事隐去。”一句。正如贾雨村是“假语村言”一样。\r\n甄士隐是和贾雨村两相对照而写的，作者在开卷第一段里就明确表示他撰拟这两个名字的寓意。写甄士隐是为了写一个经历了骨肉分离、家遭火灾、下半世坎坷而终于醒悟出世的人物形象。他可能是作者自身的影子，同时也是提系着全书主题的一个线索。', '2018-07-21 23:01:46');
INSERT INTO `t_emp` VALUES ('112', '茗烟', 'M', 'mingyan@kaisn.com', '1', '11', '茗烟，是曹雪芹小说《红楼梦》中的人物——即焙茗，老叶妈之子，贾宝玉贴身书僮。他不谙世事，淘气顽皮，是宝玉叛逆思想、叛逆行为的支持者和同情者。他年少气盛，不明事理，一出场就先演了一出大闹家塾的武戏，他并不怕得罪贾府亲戚金荣，一把揪住，出口就骂；他还洋洋得意地数落金荣姑妈。', '2018-07-21 23:06:34');
INSERT INTO `t_emp` VALUES ('113', '贾代儒', 'M', 'jiadairu@kaisn.com', '1', '13', '贾代儒是《红楼梦》中人物，贾代儒是贾府中“代”字辈的长辈，其人生有三大不幸：早年丧父，中年丧子，晚年丧孙。宝玉两次进塾就读，八股文也是跟他学的。他儿子、儿媳均早亡，故担负起对孙子的教养责任。他对贾瑞管教甚严，一旦贾瑞在外擅自过夜，他必定重罚。', '2018-07-22 21:16:10');
INSERT INTO `t_emp` VALUES ('114', '贾瑞', 'M', 'jiarui@kaisn.com', '1', '11', '贾瑞是曹雪芹小说《红楼梦》中的人物——贾府义学塾贾代儒的长孙。贪图便宜又好色，欺自己的嫂子，死于王熙凤设的“相思局”中。', '2018-07-21 23:09:57');
INSERT INTO `t_emp` VALUES ('115', '贾芸', 'M', 'jiayun@kaisn.com', '1', '11', '贾芸，《红楼梦》中人物。贾府族人，西廊下五嫂子的儿子。生有一张容长脸儿，长挑身材，甚是斯文清秀，父亲早亡。\r\n虽然比贾宝玉大上六七岁辈分上却是后者的晚辈，因聪明伶俐被宝玉戏称认作义子，另外和宝玉婢女小红（后为王熙凤婢女）有段恋情。', '2018-07-21 23:11:17');
INSERT INTO `t_emp` VALUES ('116', '贾蔷', 'M', 'jiaqiang@kaisn.com', '2', '11', '贾蔷是一个红楼梦中的男性人物，宁国府的正派玄孙。他父母早亡，从小跟贾珍过活，比贾蓉生得还风流俊俏。虽然每日应名去上学，亦只不过虚掩眼目而已，仍旧是斗鸡走狗，赏花阅柳。他上有贾珍溺爱，下有贾蓉匡助，越发自大起来。后成了贾府小戏班梨香院的总管，与小旦龄官相好。', '2018-07-21 23:12:27');
INSERT INTO `t_emp` VALUES ('117', '忠顺王', 'M', 'zhongshunwang@kaisn.com', '7', '11', '忠顺王爷是《红楼梦》中的虚拟人物，从未正面出场。年龄无可考究。不可否认的是，忠顺王爷豢养戏子，并且对这个戏子关心过度，清楚贾家的每一个小细节，并且是打垮贾家的最直接的人，但是故事里面从未点明是否有卧底在贾府。', '2018-07-21 23:18:33');
INSERT INTO `t_emp` VALUES ('118', '妙玉', 'F', 'miaoyu@kaisn.com', '8', '4', '妙玉，《红楼梦》金陵十二钗之一，苏州人氏，是一个带发修行的居士。她原是仕宦人家的小姐，自小在玄墓蟠香寺出家为尼。贾府建造大观园，妙玉入住栊翠庵。她在贾母、王夫人面前从容自若，不卑不亢；在大观园的日子里，她与宝玉、黛玉、宝钗、湘云、惜春、邢岫烟结下友谊；她美丽聪颖，心性高洁，却遭人嫉恨，举世难容；她是佛家弟子，文学上却大爱庄子，感情上又尘缘未了，不洁不空；她才华馥郁，品位高雅，栊翠庵品茶，刻画她茶艺精湛，中秋夜联诗，塑造她为“红楼诗仙”。宝玉丢失通灵宝玉，岫烟请妙玉扶乩。贾母病危，妙玉不请自来，探望病情。贾母出殡次日，妙玉被贼人掳走，宝玉悲伤叹惋。再后来，贾府传闻她在海边遇害。', '2018-07-22 09:56:01');
INSERT INTO `t_emp` VALUES ('119', '宝珠', 'F', 'baozhu@kaisn.com', '2', '4', '宝珠：中国古典小说《红楼梦》中人物。秦可卿的丫环。秦可卿死后，宝珠见秦氏没有孩子，愿为义女，请任摔丧驾灵之任，后在铁槛寺陪伴秦可卿之灵，执意不肯回宁国府。', '2018-07-22 10:02:58');
INSERT INTO `t_emp` VALUES ('120', '刘姥姥', 'F', 'liulaolao@kaisn.com', '8', '4', '刘姥姥，是中国古典文学名著《红楼梦》中人物。是一位来自乡下贫农家庭的谙于世故的老婆婆，凤姐女儿巧姐的命运与她密切相关，巧姐判词和《留余庆》曲中均提及刘姥姥。这个老婆婆深受广大读者喜爱。\r\n刘姥姥在回目上出现了四次：第6回刘姥姥一进荣国府、39回刘姥姥是信口开河、41回刘姥姥醉卧怡红院、113回忏宿冤凤姐托村妪。从篇幅上看，第6回、40回、41回三大整回，以及39回后半回、42回前半回、113回前半回、119回后半回，都是浓墨重彩的刘姥姥正传。', '2018-07-22 10:15:52');
INSERT INTO `t_emp` VALUES ('121', '王板儿', 'M', 'wangbaner@kaisn.com', '8', '11', '王板儿，中国古典文学名著《红楼梦》里刘姥姥的外孙。在小说第六回“刘姥姥一进荣国府”中，多处生动地描写了板儿未经世面的村野孩童形象。及至成年有娶贾巧姐为妻一说。', '2018-07-22 10:17:17');
INSERT INTO `t_emp` VALUES ('122', '王狗儿', 'M', 'wanggouer@kaisn.com', '8', '11', '刘姥姥的女婿王狗儿家，历史上曾与金陵王家有过一点“绕脖子”的关系（王昆仑语），就是：王狗儿的爷爷，在北京做小京官时，与金陵王家王熙凤小姐的做大京官的爷爷，曾在同一个衙门里供职，王狗儿的爷爷因为贪王熙凤的爷爷的势利，“便连了宗，认作侄儿”，于是王狗儿的爷爷成了王熙凤的爷爷的“本家”。但隔了两代以后，王狗儿家成了穷庄稼汉，金陵王家的人则成了高门大户贾府的贵夫人（王夫人）和少奶奶（王熙凤），两家人也就没来往了。', '2018-07-22 10:19:00');
INSERT INTO `t_emp` VALUES ('123', '文官', 'F', 'wenguan@kaisn.com', '10', '4', '红楼十二官原本是为了元妃省亲而在苏州采买来的。十二官的小名儿分别是文官、宝官、玉官、龄官、菂官、藕官、蕊官、茄官、芳官、葵官、豆官、艾官。从名字来看，菂官、藕官、蕊官、茄官、芳官、葵官、豆官、艾官这八官都是带草字头的。“菂”为芙蕖之实即莲子；“藕”为芙蕖根；“蕊”为花蕊；茄为芙蕖径；“芳”为花之香；葵、豆、艾亦为一年或多年生草本植物。草字头加一个“官”字是“菅”字。“菅”是多年生草本植物。', '2018-07-22 10:25:10');
INSERT INTO `t_emp` VALUES ('124', '宝官', 'F', 'baoguan@kaisn.com', '10', '4', '宝官：《红楼梦》里面的角色：首见第三十回。红楼十二官之一。贾府梨香院女伶，饰小生。她常到怡红院嬉笑玩耍。戏班解散后，即随干娘出园，单等亲父母接回原籍。', '2018-07-22 10:26:32');
INSERT INTO `t_emp` VALUES ('125', '玉官', 'F', 'yuguan@kaisn.com', '10', '4', '玉官，是曹雪芹小说《红楼梦》中的人物之一。红楼十二官之一。贾府梨香院女伶，饰正旦。红楼梦第三十回中首次出场。戏班解散后她没有留下，回乡由干娘自行聘嫁。', '2018-07-22 10:28:03');
INSERT INTO `t_emp` VALUES ('126', '龄官', 'F', 'lingguan@kaisn.com', '10', '4', '龄官是清代小说《红楼梦》中的女性人物，贾家买来的十二个唱戏的女孩之一。戏活极好，长相、气质很像林黛玉，也有着林黛玉的敏感与清高，相当于林黛玉的一个射影，素与贾蔷相好。与贾蔷的爱情也给了宝玉不一样的启发。', '2018-07-22 10:29:51');
INSERT INTO `t_emp` VALUES ('127', '菂官', 'F', 'diguan@kaisn.com', '10', '4', '菂官（dì）：一作药官，曹雪芹《红楼梦》登场人物，红楼十二官之一。系贾府买来的十二个唱戏的女孩之一，饰小旦。她在戏中与小生藕官常扮夫妻，二人相好异常，同性相恋。但不久她夭亡了，致使藕官伤心欲绝，哭得死去活来。', '2018-07-22 10:31:23');
INSERT INTO `t_emp` VALUES ('128', '藕官', 'F', 'ouguan@kaisn.com', '10', '4', '藕官是小说《红楼梦》中的女性人物。贾家买来的十二个唱戏的优伶之一，小戏子。为人义气，当她的朋友芳官被赵姨娘侮辱时，藕官冒着随时被驱逐的危险和伙伴们一起将身为主子的赵姨娘打了个痛快。戏班解散后，她成了林黛玉的丫环。先后和菂官、蕊官是同性恋人。后来因不肯被干娘转卖（聘嫁）他人而出家为尼，长伴青灯古佛。', '2018-07-22 10:32:34');
INSERT INTO `t_emp` VALUES ('129', '蕊官', 'F', 'ruiguan@kaisn.com', '10', '4', '蕊官，出自于曹雪芹小说《红楼梦》。红楼十二官之一，梨香院女伶，戏班解散后为薛宝钗的丫鬟，后遁入空门。', '2018-07-22 10:33:32');
INSERT INTO `t_emp` VALUES ('130', '茄官', 'F', 'qieguan@kaisn.com', '10', '4', '茄官，曹雪芹《红楼梦》中的女性人物（首见第五十八回），红楼十二官之一。贾府梨香院优伶，饰老旦。戏班解散后为尤氏丫鬟。', '2018-07-22 10:34:31');
INSERT INTO `t_emp` VALUES ('131', '芳官', 'F', 'fangguan@kaisn.com', '10', '4', '芳官，清代小说《红楼梦》中的女性人物，贾府买来的戏班成员，原姓花，姑苏人氏，正旦。红楼十二官之一。戏班解散后成了贾宝玉的丫环。芳官的男子气概在\"洗头事件\"和\'蔷薇硝事件\"中展现得淋漓尽致。芳官在群芳夜宴中唱《赏花时》意蕴深厚。抄检大观园时她同其他唱戏女孩子一起被撵走，她跟随水月庵的智通出家去了。', '2018-07-22 10:35:25');
INSERT INTO `t_emp` VALUES ('132', '葵官', 'F', 'kuiguan@kaisn.com', '10', '4', '葵官是曹雪芹小说《红楼梦》中的人物——贾府买来的十二个唱戏的女孩子之一。也是红楼十二官之一。贾府戏班解散后来成史湘云的丫鬟。', '2018-07-22 10:36:28');
INSERT INTO `t_emp` VALUES ('133', '豆官', 'F', 'douguan@kaisn.com', '10', '4', '豆官是《红楼梦》十二官之一，是贾家买来唱戏的女优伶。饰小花脸。身量年纪皆极小，又极鬼灵，故曰豆官。戏班解散后，随侍薛宝琴。从此，园中人或唤她“阿豆”，或唤作“炒豆子”，而薛宝琴则将她打扮成琴童摸样，唤作“豆童”。', '2018-07-22 10:37:25');
INSERT INTO `t_emp` VALUES ('134', '艾官', 'F', 'aiguan@kaisn.com', '10', '4', '艾官，《红楼梦》十二官之一，首见第五十八回。贾府梨香院女伶，饰老外。戏班解散后，她做了探春的丫头。夏婆子调唆赵姨娘辱打芳官，恰被她听见，便悄悄地回了探春，探春却料定她与芳官等“皆饰一党，本皆淘气异常，便只答应，也不肯据此为实”。众女伶被逐出大观园后，她被干娘领出自行聘嫁。', '2018-07-22 10:38:15');
INSERT INTO `t_emp` VALUES ('135', '蒋玉菡', 'M', 'jiangyuhan@kaisn.com', '8', '11', '蒋玉菡，是古典小说《红楼梦》中的人物，忠顺王府戏班的名角，擅唱小旦，小名琪官（一作棋官）。贾宝玉曾以玉玦扇坠和袭人所给松花汗巾相赠，蒋玉菡回赠以北静王所赐茜香国女国王贡奉的大红汗巾。贾府彻底败落后，蒋玉菡娶宝玉房中大丫头袭人为妻。在贾府被抄家后，蒋玉菡夫妇资助了贫穷的贾宝玉夫妇。', '2018-07-22 10:42:56');
INSERT INTO `t_emp` VALUES ('136', '春燕', 'F', 'chunyan@kaisn.com', '1', '4', '春燕，何婆子之女，贾宝玉房内的二等丫鬟，贾府内世仆的女孩，聪明伶俐乐于助人', '2018-07-22 12:46:41');
INSERT INTO `t_emp` VALUES ('137', '琥珀', 'F', 'hupo@kaisn.com', '1', '4', '琥珀，贾母身边的大丫鬟，照顾贾母起居生活，也负责传话、取物等。', '2018-07-22 12:49:05');
INSERT INTO `t_emp` VALUES ('138', '彩云', 'F', 'caiyun@kaisn.com', '5', '4', '彩云是小说《红楼梦》里的人物，是一个与贾环玩得很要好的丫头。贾环曾为她从芳官处讨些蔷薇硝来，彩云打开一看才知是茉莉粉，赵姨娘非要找芳官算帐，彩云死劝不住，只得躲入房内。后来，她禁不住赵姨娘的再三央求，从王夫人柜子里偷了些东西给贾环，被玉钏儿发觉。平儿为了不使探春难堪，要宝玉应下拿东西的名儿，彩云回来告诉赵姨娘。贾环起了疑心，认为她和宝玉好，并把彩云私赠之物都拿出来，照著她的脸摔了过去。彩云急得赌咒起誓，百般解说。贾环还是不信，气得彩云哭了个泪乾肠断，把东西全扔到河里了。后来她染了无医之症。', '2018-07-22 14:54:04');
INSERT INTO `t_emp` VALUES ('139', '雪雁', 'F', 'xueyan@kaisn.com', '6', '4', '雪雁是中国古典小说《红楼梦》中的人物，雪雁是林黛玉从苏州家里带来的小丫头，为林黛玉的贴身丫头之一。', '2018-07-22 15:05:03');
INSERT INTO `t_emp` VALUES ('140', '碧痕', 'F', 'bihen@kaisn.com', '1', '4', '碧痕是贾宝玉房里的一个丫鬟。曾经在红楼梦中第20，24，26，31，77回出现过，碧痕的长相在书中从未出现过，有两次描写她的牙尖嘴利，心气很高。', '2018-07-22 15:16:48');
INSERT INTO `t_emp` VALUES ('141', '秋纹', 'F', 'qiuwen@kaisn.com', '1', '4', '秋纹，荣国府秋纹《红楼梦》中贾宝玉的小丫环，一味小心服侍宝玉。属《红楼梦》一百零八钗情榜四副榜十二名中人。', '2018-07-22 15:23:31');
INSERT INTO `t_emp` VALUES ('142', '善姐', 'F', 'shanjie@kaisn.com', '1', '4', '善姐，曹雪芹《红楼梦》中登场人物，本为王熙凤身边的丫头，后被凤姐特意送给尤二姐使唤。\"善姐\"二字实为反讽:名为\"善\"，但其人不善。 善姐伶牙俐齿，时时对尤二姐冷嘲热讽，又\"不服使唤\"，故意在饮食起居上作践二姐。善姐之\"不善\"，实为凤姐逼死尤二姐的阴险步骤之一，故《脂》批说:\"写使女欺压二姐，正写凤姐欺压二姐。', '2018-07-22 21:12:54');

-- ----------------------------
-- Table structure for t_pos
-- ----------------------------
DROP TABLE IF EXISTS `t_pos`;
CREATE TABLE `t_pos` (
  `pos_id` int(11) NOT NULL AUTO_INCREMENT,
  `pos_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`pos_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_pos
-- ----------------------------
INSERT INTO `t_pos` VALUES ('1', '老太太');
INSERT INTO `t_pos` VALUES ('2', '管家');
INSERT INTO `t_pos` VALUES ('3', '小姐');
INSERT INTO `t_pos` VALUES ('4', '丫环');
INSERT INTO `t_pos` VALUES ('5', '书童');
INSERT INTO `t_pos` VALUES ('6', '老爷');
INSERT INTO `t_pos` VALUES ('7', '太太');
INSERT INTO `t_pos` VALUES ('8', '姨太太');
INSERT INTO `t_pos` VALUES ('9', '爷');
INSERT INTO `t_pos` VALUES ('10', '奶奶');
INSERT INTO `t_pos` VALUES ('11', '奴才');
INSERT INTO `t_pos` VALUES ('12', '王爷');
INSERT INTO `t_pos` VALUES ('13', '其他');