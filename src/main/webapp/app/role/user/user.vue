<template>
  <div class="role-user-wrap">
    <el-page-header content="新建用户"> </el-page-header>
    <el-form class="m-t-30">
      <el-form-item label="用户手机号">
        <div class="row">
          <el-input class="input" v-model="userPhone"></el-input> <el-button class="m-l-30" type="primary">搜索</el-button>
        </div>
      </el-form-item>
      <el-form-item label="授权部门">
        <div class="row">
          <el-tree ref="tree" :props="props" :load="loadNode" lazy show-checkbox @check-change="handleCheckChange"> </el-tree>
        </div>
      </el-form-item>
      <el-form-item label="授权部门">{{ selectedNodes.map(t => t.name).join(',') }} </el-form-item>
    </el-form>
  </div>
</template>
<script lang="ts" src="./user.component.ts"></script>
<style lang="scss" scoped>
.m-t-30 {
  margin-top: 30px;
}

.m-l-30 {
  margin-left: 30px;
}

.role-user-wrap {
  background-color: transparent;
}

.row {
  display: flex;
  justify-content: flex-start;
  align-items: center;
}

.input {
  flex: 1 1 auto;
  width: auto;
}
</style>
