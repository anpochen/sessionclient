package com.session.client;

import java.io.File;
import java.io.IOException;

import javax.xml.validation.Validator;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

/**
 * @author Administrator
 * 根据Schema xsd文件验证 xml 文件
 */
public class XmlValidator {

    /**
     * 根据Schema xsd文件验证 xml 文件
     *
     * @param xmlPath：xml 文件的路径
     * @param xsdPath：xsd 文件的路径
     * @return：返回验证结果
     */
    public static boolean validate(String xmlPath, String xsdPath) {
        boolean flag = false;
        //获取当前工程的 classpath 的 绝对路径uri
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        //basePath = /D:/Eclipse/eclipse/SalaryManager/build/classes/

        //构建绝对路径
        xmlPath = basePath + xmlPath;
        xsdPath = basePath + xsdPath;

        try {

            //查找支持指定模式语言的  SchemaFactory 的实现并返回它
            SchemaFactory factory =
                    SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

            //解析作为模式的指定 File 并以 Schema 的形式返回它
            //此对象表示可以根据  XML 文档检查/实施的约束集
            File file = new File(xsdPath);
            Schema schema = factory.newSchema(file);

            //验证器实施/检查此对象表示的约束集。Validator -> 根据 Schema 检查 XML 文档的处理器。
            Validator validator = schema.newValidator();

            //验证指定的输入。 Source -> 实现此接口的对象包含充当源输入（XML 源或转换指令）所需的信息
            Source source = new StreamSource(xmlPath);
            validator.validate(source);

            flag = true;
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return flag;
    }
}
