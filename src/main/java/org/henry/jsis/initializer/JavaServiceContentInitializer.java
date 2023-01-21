package org.henry.jsis.initializer;

import org.henry.jsis.services.*;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Henry Azer
 * @since 21/01/2023
 **/
public class JavaServiceContentInitializer {
    private static final String DATE = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

    public static String initJavaDaoContent(JavaDao dao) {
        return "package " + dao.getJavaServiceInfo().getServicePackage() + ".dao;\n" +
                "\n" +
                "import com.ntsal.baseservice.dao.BaseDao;\n" +
                "import " + dao.getJavaServiceInfo().getServicePackage() + ".dao.repo." + dao.getJavaServiceInfo().getServiceName() + "Repo;\n" +
                "import " + dao.getJavaServiceInfo().getServicePackage() + ".model." + dao.getJavaServiceInfo().getServiceName() + ";\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "public interface " + dao.getJavaServiceInfo().getServiceName() + "Dao extends BaseDao<" + dao.getJavaServiceInfo().getServiceName() + ", " + dao.getJavaServiceInfo().getServiceName() + "Repo> {\n" +
                "}";
    }

    public static String initJavaDaoImplContent(JavaDaoImpl daoImpl) {
        return "package " + daoImpl.getJavaServiceInfo().getServicePackage() + ".dao;\n" +
                "\n" +
                "import " + daoImpl.getJavaServiceInfo().getServicePackage() + ".dao.repo." + daoImpl.getJavaServiceInfo().getServiceName() + "Repo;\n" +
                "import org.springframework.stereotype.Component;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@Component\n" +
                "public class " + daoImpl.getJavaServiceInfo().getServiceName() + "DaoImpl implements " + daoImpl.getJavaServiceInfo().getServiceName() + "Dao {\n" +
                "    private final " + daoImpl.getJavaServiceInfo().getServiceName() + "Repo " + daoImpl.getJavaServiceInfo().getServiceNameUncapitalized() + "Repo;\n" +
                "\n" +
                "    public " + daoImpl.getJavaServiceInfo().getServiceName() + "DaoImpl(" + daoImpl.getJavaServiceInfo().getServiceName() + "Repo " + daoImpl.getJavaServiceInfo().getServiceNameUncapitalized() + "Repo) {\n" +
                "        this." + daoImpl.getJavaServiceInfo().getServiceNameUncapitalized() + "Repo = " + daoImpl.getJavaServiceInfo().getServiceNameUncapitalized() + "Repo;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public " + daoImpl.getJavaServiceInfo().getServiceName() + "Repo getRepository() {\n" +
                "        return " + daoImpl.getJavaServiceInfo().getServiceNameUncapitalized() + "Repo;\n" +
                "    }\n" +
                "}";
    }

    public static String initJavaRepositoryContent(JavaRepository repository) {
        return "package " + repository.getJavaServiceInfo().getServicePackage() + ".repo;\n" +
                "\n" +
                "import " + repository.getJavaServiceInfo().getServicePackage() + ".model." + repository.getJavaServiceInfo().getServiceName() + ";\n" +
                "import org.springframework.data.jpa.repository.JpaRepository;\n" +
                "import org.springframework.stereotype.Repository;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@Repository\n" +
                "public interface " + repository.getJavaServiceInfo().getServiceName() + "Repo extends JpaRepository<" + repository.getJavaServiceInfo().getServiceName() + ", Long> {\n" +
                "}";
    }

    public static String initJavaTransformerContent(JavaTransformer transformer) {
        return "package " + transformer.getJavaServiceInfo().getServicePackage() + ".transformer;\n" +
                "\n" +
                "import com.ntsal.baseservice.transformer.BaseTransformer;\n" +
                "import " + transformer.getJavaServiceInfo().getServicePackage() + ".dto." + transformer.getJavaServiceInfo().getServiceName() + "Dto;\n" +
                "import " + transformer.getJavaServiceInfo().getServicePackage() + ".model." + transformer.getJavaServiceInfo().getServiceName() + ";\n" +
                "import " + transformer.getJavaServiceInfo().getServicePackage() + ".transformer.mapper." + transformer.getJavaServiceInfo().getServiceName() + "Mapper;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import org.springframework.stereotype.Component;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@Component\n" +
                "@AllArgsConstructor\n" +
                "public class " + transformer.getJavaServiceInfo().getServiceName() + "Transformer implements BaseTransformer<" + transformer.getJavaServiceInfo().getServiceName() + ", " + transformer.getJavaServiceInfo().getServiceName() + "Dto, " + transformer.getJavaServiceInfo().getServiceName() + "Mapper> {\n" +
                "    private final " + transformer.getJavaServiceInfo().getServiceName() + "Mapper " + transformer.getJavaServiceInfo().getServiceNameUncapitalized() + "Mapper;\n" +
                "\n" +
                "    @Override\n" +
                "    public " + transformer.getJavaServiceInfo().getServiceName() + "Mapper getMapper() {\n" +
                "        return " + transformer.getJavaServiceInfo().getServiceNameUncapitalized() + "Mapper;\n" +
                "    }\n" +
                "}";
    }

    public static String initJavaMapperContent(JavaMapper mapper) {
        return "package " + mapper.getJavaServiceInfo().getServicePackage() + ".transformer.mapper;\n" +
                "\n" +
                "import com.ntsal.baseservice.transformer.mapper.BaseMapper;\n" +
                "import com.ntsal.baseservice.transformer.mapper.MapStructCentralConfig;\n" +
                "import " + mapper.getJavaServiceInfo().getServicePackage() + ".dto." + mapper.getJavaServiceInfo().getServiceName() + "Dto;\n" +
                "import " + mapper.getJavaServiceInfo().getServicePackage() + ".model." + mapper.getJavaServiceInfo().getServiceName() + ";\n" +
                "import org.mapstruct.InjectionStrategy;\n" +
                "import org.mapstruct.Mapper;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@Mapper(componentModel = \"spring\", injectionStrategy = InjectionStrategy.CONSTRUCTOR, config = MapStructCentralConfig.class)\n" +
                "public interface " + mapper.getJavaServiceInfo().getServiceName() + "Mapper extends BaseMapper<" + mapper.getJavaServiceInfo().getServiceName() + ", " + mapper.getJavaServiceInfo().getServiceName() + "Dto> {\n" +
                "}";
    }

    public static String initJavaServiceContent(JavaService service) {
        return "package " + service.getJavaServiceInfo().getServicePackage() + ".service;\n" +
                "\n" +
                "import com.ntsal.baseservice.service.BaseService;\n" +
                "import " + service.getJavaServiceInfo().getServicePackage() + ".dao." + service.getJavaServiceInfo().getServiceName() + "Dao;\n" +
                "import " + service.getJavaServiceInfo().getServicePackage() + ".dto." + service.getJavaServiceInfo().getServiceName() + "Dto;\n" +
                "import " + service.getJavaServiceInfo().getServicePackage() + ".model." + service.getJavaServiceInfo().getServiceName() + ";\n" +
                "import " + service.getJavaServiceInfo().getServicePackage() + ".transformer." + service.getJavaServiceInfo().getServiceName() + "Transformer;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "public interface " + service.getJavaServiceInfo().getServiceName() + "Service extends BaseService<" + service.getJavaServiceInfo().getServiceName() + ", " + service.getJavaServiceInfo().getServiceName() + "Dto, " + service.getJavaServiceInfo().getServiceName() + "Transformer, " + service.getJavaServiceInfo().getServiceName() + "Dao> {\n" +
                "}";
    }

    public static String initJavaServiceImplContent(JavaServiceImpl serviceImpl) {
        return "package " + serviceImpl.getJavaServiceInfo().getServicePackage() + ".service;\n" +
                "\n" +
                "import com.ntsal.baseservice.service.MessageService;\n" +
                "import " + serviceImpl.getJavaServiceInfo().getServicePackage() + ".dao." + serviceImpl.getJavaServiceInfo().getServiceName() + "Dao;\n" +
                "import " + serviceImpl.getJavaServiceInfo().getServicePackage() + ".transformer." + serviceImpl.getJavaServiceInfo().getServiceName() + "Transformer;\n" +
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
                "public class " + serviceImpl.getJavaServiceInfo().getServiceName() + "ServiceImpl implements " + serviceImpl.getJavaServiceInfo().getServiceName() + "Service {\n" +
                "    private final " + serviceImpl.getJavaServiceInfo().getServiceName() + "Transformer " + serviceImpl.getJavaServiceInfo().getServiceNameUncapitalized() + "Transformer;\n" +
                "    private final " + serviceImpl.getJavaServiceInfo().getServiceName() + "Dao " + serviceImpl.getJavaServiceInfo().getServiceNameUncapitalized() + "Dao;\n" +
                "    private final MessageService messageService;\n" +
                "\n" +
                "    @Override\n" +
                "    public " + serviceImpl.getJavaServiceInfo().getServiceName() + "Transformer getTransformer() {\n" +
                "        return " + serviceImpl.getJavaServiceInfo().getServiceNameUncapitalized() + "Transformer;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public " + serviceImpl.getJavaServiceInfo().getServiceName() + "Dao getDao() {\n" +
                "        return " + serviceImpl.getJavaServiceInfo().getServiceNameUncapitalized() + "Dao;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public MessageService getMessageService() {\n" +
                "        return messageService;\n" +
                "    }\n" +
                "}";
    }

    public static String initJavaControllerContent(JavaController controller) {
        return "package " + controller.getJavaServiceInfo().getServicePackage() + ".controller;\n" +
                "\n" +
                "import com.ntsal.baseservice.api.response.ApiResponse;\n" +
                "import com.ntsal.baseservice.api.response.ApiResponseBuilder;\n" +
                "import com.ntsal.baseservice.controller.BaseController;\n" +
                "import " + controller.getJavaServiceInfo().getServicePackage() + ".dto." + controller.getJavaServiceInfo().getServiceName() + "Dto;\n" +
                "import " + controller.getJavaServiceInfo().getServicePackage() + ".service." + controller.getJavaServiceInfo().getServiceName() + "Service;\n" +
                "import lombok.AllArgsConstructor;\n" +
                "import org.springframework.web.bind.annotation.*;\n" +
                "\n" +
                "/**\n" +
                " * @author Henry Azer\n" +
                " * @since " + DATE + "\n" +
                " */\n" +
                "@RestController\n" +
                "@AllArgsConstructor\n" +
                "@RequestMapping(\"/api/" + controller.getJavaServiceInfo().getServiceNameUncapitalized() + "\")\n" +
                "public class " + controller.getJavaServiceInfo().getServiceName() + "JavaController implements BaseController<" + controller.getJavaServiceInfo().getServiceName() + "Service, " + controller.getJavaServiceInfo().getServiceName() + "Dto> {\n" +
                "    private final ApiResponseBuilder<" + controller.getJavaServiceInfo().getServiceName() + "Dto> apiResponseBuilder;\n" +
                "    private final " + controller.getJavaServiceInfo().getServiceName() + "Service " + controller.getJavaServiceInfo().getServiceNameUncapitalized() + "Service;\n" +
                "\n" +
                "    @Override\n" +
                "    public " + controller.getJavaServiceInfo().getServiceName() + "Service getService() {\n" +
                "        return " + controller.getJavaServiceInfo().getServiceNameUncapitalized() + "Service;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public ApiResponseBuilder<" + controller.getJavaServiceInfo().getServiceName() + "Dto> getApiResponseBuilder() {\n" +
                "        return apiResponseBuilder;\n" +
                "    }\n" +
                "\n" +
                "}";
    }
}