package com.topjet.manage.common.dynamicpassword.generateRQCode;

import com.topjet.common.SessionUtils;
import com.topjet.manage.common.dynamicpassword.constant.DynamicPasswordConstant;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;
import org.apache.log4j.Logger;

import java.io.*;

/*  需要引入的maven依赖
 * <dependency>
 <groupId>net.glxn</groupId>
 <artifactId>qrgen</artifactId>
 <version>1.2</version>
 </dependency>
 */
public class RQCode {
	private final static Logger log = Logger.getLogger(RQCode.class);

	public static void generateRQcode(String path,String initData) {
		try {
			ByteArrayOutputStream out = QRCode.from(initData).to(ImageType.PNG).stream();
			FileOutputStream fout = new FileOutputStream(new File(path+ DynamicPasswordConstant.PATH+ SessionUtils.getAttr("userId")+"QR_Code.JPG"));
			fout.write(out.toByteArray());
			fout.flush();
			fout.close();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage());

		} catch (IOException e1) {
			log.error(e1.getMessage());
		}
	}

}
