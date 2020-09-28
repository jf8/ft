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
    return {
      props: {
        label: 'name',
        children: 'zones',
      },
      count: 1,
      userPhone: '',
      selectedNodes: [],
    };
  },
})
export default class User extends Vue {
  count: number;
  selectedNodes: Array<Object>;
  handleCheckChange(data, checked, indeterminate) {
    // console.log( this.$refs.tree.getCheckedNodes());
    // console.log(this.$refs.tree.getCheckedNodes());
    this.selectedNodes = this.$refs.tree.getCheckedNodes();
  }
  handleNodeClick(data) {
    // console.log(data);
    // console.log();
  }
  loadNode(node, resolve) {
    if (node.level === 0) {
      return resolve([{ name: 'region1' }, { name: 'region2' }]);
    }
    if (node.level > 3) return resolve([]);

    var hasChild;
    if (node.data.name === 'region1') {
      hasChild = true;
    } else if (node.data.name === 'region2') {
      hasChild = false;
    } else {
      hasChild = Math.random() > 0.5;
    }

    setTimeout(() => {
      var data;
      if (hasChild) {
        data = [
          {
            name: 'zone' + this.count++,
          },
          {
            name: 'zone' + this.count++,
          },
        ];
      } else {
        data = [];
      }

      resolve(data);
    }, 500);
  }
}
