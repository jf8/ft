import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import { Tree, Row, Col, Button, PageHeader, Form, FormItem, Input } from 'element-ui';
@Component({
  components: {
    [Row.name]: Row,
    [Col.name]: Col,
    [PageHeader.name]: PageHeader,
    [Button.name]: Button,
    [Form.name]: Form,
    [FormItem.name]: FormItem,
    [Input.name]: Input,
    [Tree.name]: Tree,
  },
  data() {
    return {};
  },
})
export default class List extends Vue {}
