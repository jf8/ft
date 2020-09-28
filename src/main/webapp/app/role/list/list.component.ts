import Component from 'vue-class-component';
import { Inject, Vue } from 'vue-property-decorator';
import { Table, TableColumn, Row, Col, Button, PageHeader, Form, FormItem, Input } from 'element-ui';
@Component({
  components: {
    [Table.name]: Table,
    [TableColumn.name]: TableColumn,
    [Row.name]: Row,
    [Col.name]: Col,
    [PageHeader.name]: PageHeader,
    [Button.name]: Button,
    [Form.name]: Form,
    [FormItem.name]: FormItem,
    [Input.name]: Input,
  },
  data() {
    return {
      tableData: [
        {
          date: '2016-05-02',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1518 弄',
          zip: 200333,
        },
        {
          date: '2016-05-04',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1517 弄',
          zip: 200333,
        },
        {
          date: '2016-05-01',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1519 弄',
          zip: 200333,
        },
        {
          date: '2016-05-03',
          name: '王小虎',
          province: '上海',
          city: '普陀区',
          address: '上海市普陀区金沙江路 1516 弄',
          zip: 200333,
        },
      ],
    };
  },
})
export default class List extends Vue {
  handleClick(row) {
    console.log(row);
  }
}
