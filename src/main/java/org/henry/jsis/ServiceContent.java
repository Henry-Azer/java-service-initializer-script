package org.henry.jsis;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Henry Azer
 * @since 21/01/2023
 **/
public class ServiceContent {
    private static final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    public static String getServiceContent(String serviceName, String serviceFileName, String packageName) {
        if (serviceFileName.contains(Service.DAO.getName()))
            return initDaoContent(serviceName, packageName);
        else if (serviceFileName.contains(Service.DAO_IMPL.getName()))
            return initDaoImplContent(serviceName, packageName);
        else if (serviceFileName.contains(Service.REPOSITORY.getName()))
            return initRepositoryContent(serviceName, packageName);
        else if (serviceFileName.contains(Service.TRANSFORMER.getName()))
            return initTransformerContent(serviceName, packageName);
        else if (serviceFileName.contains(Service.MAPPER.getName()))
            return initMapperContent(serviceName, packageName);
        else if (serviceFileName.contains(Service.SERVICE.getName()))
            return initServiceContent(serviceName, packageName);
        else if (serviceFileName.contains(Service.SERVICE_IMPL.getName()))
            return initServiceImplContent(serviceName, packageName);
        else if (serviceFileName.contains(Service.CONTROLLER.getName()))
            return initControllerContent(serviceName, packageName);
        else return "";
    }

    private static String initDaoContent(String serviceName, String packageName) {
        return "package " + packageName + ".dao;\n" +
                "\n" +
                "import com.ntsal.baseservice.dao.BaseDao;\n" +
                "import " + packageName + ".dao.repo." + serviceName + "Repo;\n" +
                "import " + packageName + ".model." + serviceName + ";\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "public interface " + serviceName + "Dao extends BaseDao<" + serviceName + ", " + serviceName + "Repo> {\n" +
                "}";
    }

    private static String initDaoImplContent(String serviceName, String packageName) {
        String uncapitalizedServiceName = uncapitalize(serviceName);
        return "package " + packageName + ".dao;\n" +
                "\n" +
                "import " + packageName + ".dao.repo." + serviceName + "Repo;\n" +
                "import org.springframework.stereotype.Component;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@Component\n" +
                "public class " + serviceName + "DaoImpl implements " + serviceName + "Dao {\n" +
                "    private final " + serviceName + "Repo " + uncapitalizedServiceName + "Repo;\n" +
                "\n" +
                "    public " + serviceName + "DaoImpl(" + serviceName + "Repo " + uncapitalizedServiceName + "Repo) {\n" +
                "        this." + uncapitalizedServiceName + "Repo = " + uncapitalizedServiceName + "Repo;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public " + serviceName + "Repo getRepository() {\n" +
                "        return " + uncapitalizedServiceName + "Repo;\n" +
                "    }\n" +
                "}";
    }

    private static String initRepositoryContent(String serviceName, String packageName) {
        return "package " + packageName + ".repo;\n" +
                "\n" +
                "import " + packageName + ".model." + serviceName + ";\n" +
                "import org.springframework.data.jpa.repository.JpaRepository;\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@Repository\n" +
                "public interface " + serviceName + "Repo extends JpaRepository<" + serviceName + ", Long> {\n" +
                "}";
    }

    private static String initTransformerContent(String serviceName, String packageName) {
        String uncapitalizedServiceName = uncapitalize(serviceName);
        return "package " + packageName + ".transformer;\n" +
                "\n" +
                "import com.ntsal.baseservice.transformer.BaseTransformer;\n" +
                "import " + packageName + ".dto." + serviceName + "Dto;\n" +
                "import " + packageName + ".model." + serviceName + ";\n" +
                "import " + packageName + ".transformer.mapper." + serviceName + "Mapper;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import org.springframework.stereotype.Component;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@Component\n" +
                "@AllArgsConstructor\n" +
                "public class " + serviceName + "Transformer implements BaseTransformer<" + serviceName + ", " + serviceName + "Dto, " + serviceName + "Mapper> {\n" +
                "    private final " + serviceName + "Mapper " + uncapitalizedServiceName + "Mapper;\n" +
                "\n" +
                "    @Override\n" +
                "    public " + serviceName + "Mapper getMapper() {\n" +
                "        return " + uncapitalizedServiceName + "Mapper;\n" +
                "    }\n" +
                "}";
    }

    private static String initMapperContent(String serviceName, String packageName) {
        return "package " + packageName + ".transformer.mapper;\n" +
                "\n" +
                "import com.ntsal.baseservice.transformer.mapper.BaseMapper;\n" +
                "import com.ntsal.baseservice.transformer.mapper.MapStructCentralConfig;\n" +
                "import" + packageName + ".dto." + serviceName + "Dto;\n" +
                "import" + packageName + ".model." + serviceName + ";\n" +
                "import org.mapstruct.InjectionStrategy;\n" +
                "import org.mapstruct.Mapper;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@Mapper(componentModel = \"spring\", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = MapStructCentralConfig.class)\n" +
                "public interface " + serviceName + "Mapper extends BaseMapper<" + serviceName + ", " + serviceName + "Dto> {\n" +
                "}";
    }

    private static String initServiceContent(String serviceName, String packageName) {
        return "package " + packageName + ".service;\n" +
                "\n" +
                "import com.ntsal.baseservice.service.BaseService;\n" +
                "import " + packageName + ".dao." + serviceName + "Dao;\n" +
                "import " + packageName + ".dto." + serviceName + "Dto;\n" +
                "import " + packageName + ".model." + serviceName + ";\n" +
                "import " + packageName + ".transformer." + serviceName + "Transformer;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "public interface " + serviceName + "Service extends BaseService<" + serviceName + ", " + serviceName + "Dto, " + serviceName + "Transformer, " + serviceName + "Dao> {\n" +
                "}";
    }

    private static String initServiceImplContent(String serviceName, String packageName) {
        String uncapitalizedServiceName = uncapitalize(serviceName);
        return "package " + packageName + ".service;\n" +
                "\n" +
                "import com.ntsal.baseservice.service.MessageService;\n" +
                "import " + packageName + ".dao." + serviceName + "Dao;\n" +
                "import " + packageName + ".transformer." + serviceName + "Transformer;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import lombok.extern.slf4j.Slf4j;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@Slf4j\n" +
                "@Service\n" +
                "@AllArgsConstructor\n" +
                "public class " + serviceName + "ServiceImpl implements " + serviceName + "Service {\n" +
                "    private final " + serviceName + "Transformer " + uncapitalizedServiceName + "Transformer;\n" +
                "    private final " + serviceName + "Dao " + uncapitalizedServiceName + "Dao;\n" +
                "    private final MessageService messageService;\n" +
                "\n" +
                "    @Override\n" +
                "    public " + serviceName + "Transformer getTransformer() {\n" +
                "        return " + uncapitalizedServiceName + "Transformer;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public " + serviceName + "Dao getDao() {\n" +
                "        return " + uncapitalizedServiceName + "Dao;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public MessageService getMessageService() {\n" +
                "        return messageService;\n" +
                "    }\n" +
                "}";
    }

    private static String initControllerContent(String serviceName, String packageName) {
        String uncapitalizedServiceName = uncapitalize(serviceName);
        return "package " + packageName + ".controller;\n" +
                "\n" +
                "import com.ntsal.baseservice.api.response.ApiResponse;\n" +
                "import com.ntsal.baseservice.api.response.ApiResponseBuilder;\n" +
                "import com.ntsal.baseservice.controller.BaseController;\n" +
                "import " + packageName + ".dto." + serviceName + "Dto;\n" +
                "import " + packageName + ".service." + serviceName + "Service;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import org.springframework.web.bind.annotation.*;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@RestController\n" +
                "@AllArgsConstructor\n" +
                "@RequestMapping(\"/api/" + uncapitalizedServiceName + "\")\n" +
                "public class " + serviceName + "Controller implements BaseController<" + serviceName + "Service, " + serviceName + "Dto> {\n" +
                "    private final " + serviceName + "Service " + uncapitalizedServiceName + "Service;\n" +
                "    private final ApiResponseBuilder<" + serviceName + "Dto> apiResponseBuilder;\n" +
                "\n" +
                "    @Override\n" +
                "    public " + serviceName + "Service getService() {\n" +
                "        return " + uncapitalizedServiceName + "Service;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public ApiResponseBuilder<" + serviceName + "Dto> getApiResponseBuilder() {\n" +
                "        return apiResponseBuilder;\n" +
                "    }\n" +
                "\n" +
                "}";
    }

    public static String uncapitalize(String str) {
        return uncapitalize(str, (char[]) null);
    }

    public static String uncapitalize(String str, char... delimiters) {
        int delimLen = delimiters == null ? -1 : delimiters.length;
        if (!str.isEmpty() && delimLen != 0) {
            char[] buffer = str.toCharArray();
            boolean uncapitalizeNext = true;
            for (int i = 0; i < buffer.length; ++i) {
                char ch = buffer[i];
                if (isDelimiter(ch, delimiters)) {
                    uncapitalizeNext = true;
                } else if (uncapitalizeNext) {
                    buffer[i] = Character.toLowerCase(ch);
                    uncapitalizeNext = false;
                }
            }
            return new String(buffer);
        } else {
            return str;
        }
    }

    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        } else {
            for (char delimiter : delimiters) {
                if (ch == delimiter) {
                    return true;
                }
            }
            return false;
        }
    }
}