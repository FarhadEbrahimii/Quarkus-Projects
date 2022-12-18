package ir.farhad.filters;

import org.eclipse.microprofile.openapi.OASFilter;
import org.eclipse.microprofile.openapi.models.Operation;

import java.util.List;

public class OpenApiFilter implements OASFilter {

    @Override
    public Operation filterOperation(Operation operation) {
        System.out.println("call method filter operation . . .");
        if(operation.getOperationId().equals("closeAccount")){
            operation.setTags(List.of("close-account"));
        }
        return operation;
    }
}
