package trevo.maquinas.api.response;



public class ResponseModelObject extends ResponseModel {
    private Object object;
    public ResponseModelObject (String message, Object object) {
        this.setObject(object);
        this.setMessage(message);
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
