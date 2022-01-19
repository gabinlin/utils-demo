package com.example.xmind;

import org.xmind.core.*;
import org.xmind.core.io.ByteArrayStorage;

import java.io.File;
import java.io.IOException;

public class LoadXmindTest {

    public static void main(String[] args) {
        IWorkbookBuilder builder = Core.getWorkbookBuilder();//初始化builder
        IWorkbook workbook = null;
        String path = "/Users/linjiabin/Downloads/demo.xmind";
        try {
            workbook = builder.loadFromFile(new File(path), new ByteArrayStorage(), null);//打开XMind文件
        } catch (IOException | CoreException e) {
            e.printStackTrace();
        }
        assert workbook != null;
        ISheet defSheet = workbook.getPrimarySheet();//获取主Sheet
        ITopic rootTopic = defSheet.getRootTopic();//获取根Topic
        System.out.println(rootTopic.getTitleText());
        rootTopic.getAllChildren().forEach(a -> {
            System.out.println(a.getTitleText());
            a.getAllChildren().forEach(b -> {
                System.out.println(b.getTitleText());
                INotesContent content = b.getNotes().getContent(INotes.PLAIN);
                IPlainNotesContent plainNotesContent = (IPlainNotesContent) content;
                System.out.println(plainNotesContent.getTextContent());

            });
        });
    }
}
