<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ftApp.ddBookDept.home.createOrEditLabel" v-text="$t('ftApp.ddBookDept.home.createOrEditLabel')">Create or edit a DdBookDeptFt</h2>
                <div>
                    <div class="form-group" v-if="ddBookDept.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="ddBookDept.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.name')" for="dd-book-dept-ft-name">Name</label>
                        <input type="text" class="form-control" name="name" id="dd-book-dept-ft-name"
                            :class="{'valid': !$v.ddBookDept.name.$invalid, 'invalid': $v.ddBookDept.name.$invalid }" v-model="$v.ddBookDept.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.orderNum')" for="dd-book-dept-ft-orderNum">Order Num</label>
                        <input type="number" class="form-control" name="orderNum" id="dd-book-dept-ft-orderNum"
                            :class="{'valid': !$v.ddBookDept.orderNum.$invalid, 'invalid': $v.ddBookDept.orderNum.$invalid }" v-model.number="$v.ddBookDept.orderNum.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.parentid')" for="dd-book-dept-ft-parentid">Parentid</label>
                        <input type="number" class="form-control" name="parentid" id="dd-book-dept-ft-parentid"
                            :class="{'valid': !$v.ddBookDept.parentid.$invalid, 'invalid': $v.ddBookDept.parentid.$invalid }" v-model.number="$v.ddBookDept.parentid.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.createDeptGroup')" for="dd-book-dept-ft-createDeptGroup">Create Dept Group</label>
                        <input type="checkbox" class="form-check" name="createDeptGroup" id="dd-book-dept-ft-createDeptGroup"
                            :class="{'valid': !$v.ddBookDept.createDeptGroup.$invalid, 'invalid': $v.ddBookDept.createDeptGroup.$invalid }" v-model="$v.ddBookDept.createDeptGroup.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.autoAddUser')" for="dd-book-dept-ft-autoAddUser">Auto Add User</label>
                        <input type="checkbox" class="form-check" name="autoAddUser" id="dd-book-dept-ft-autoAddUser"
                            :class="{'valid': !$v.ddBookDept.autoAddUser.$invalid, 'invalid': $v.ddBookDept.autoAddUser.$invalid }" v-model="$v.ddBookDept.autoAddUser.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.deptHiding')" for="dd-book-dept-ft-deptHiding">Dept Hiding</label>
                        <input type="checkbox" class="form-check" name="deptHiding" id="dd-book-dept-ft-deptHiding"
                            :class="{'valid': !$v.ddBookDept.deptHiding.$invalid, 'invalid': $v.ddBookDept.deptHiding.$invalid }" v-model="$v.ddBookDept.deptHiding.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.deptPermits')" for="dd-book-dept-ft-deptPermits">Dept Permits</label>
                        <input type="text" class="form-control" name="deptPermits" id="dd-book-dept-ft-deptPermits"
                            :class="{'valid': !$v.ddBookDept.deptPermits.$invalid, 'invalid': $v.ddBookDept.deptPermits.$invalid }" v-model="$v.ddBookDept.deptPermits.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.userPermits')" for="dd-book-dept-ft-userPermits">User Permits</label>
                        <input type="text" class="form-control" name="userPermits" id="dd-book-dept-ft-userPermits"
                            :class="{'valid': !$v.ddBookDept.userPermits.$invalid, 'invalid': $v.ddBookDept.userPermits.$invalid }" v-model="$v.ddBookDept.userPermits.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.outerDept')" for="dd-book-dept-ft-outerDept">Outer Dept</label>
                        <input type="checkbox" class="form-check" name="outerDept" id="dd-book-dept-ft-outerDept"
                            :class="{'valid': !$v.ddBookDept.outerDept.$invalid, 'invalid': $v.ddBookDept.outerDept.$invalid }" v-model="$v.ddBookDept.outerDept.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.outerPermitDepts')" for="dd-book-dept-ft-outerPermitDepts">Outer Permit Depts</label>
                        <input type="text" class="form-control" name="outerPermitDepts" id="dd-book-dept-ft-outerPermitDepts"
                            :class="{'valid': !$v.ddBookDept.outerPermitDepts.$invalid, 'invalid': $v.ddBookDept.outerPermitDepts.$invalid }" v-model="$v.ddBookDept.outerPermitDepts.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.outerPermitUsers')" for="dd-book-dept-ft-outerPermitUsers">Outer Permit Users</label>
                        <input type="text" class="form-control" name="outerPermitUsers" id="dd-book-dept-ft-outerPermitUsers"
                            :class="{'valid': !$v.ddBookDept.outerPermitUsers.$invalid, 'invalid': $v.ddBookDept.outerPermitUsers.$invalid }" v-model="$v.ddBookDept.outerPermitUsers.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.orgDeptOwner')" for="dd-book-dept-ft-orgDeptOwner">Org Dept Owner</label>
                        <input type="text" class="form-control" name="orgDeptOwner" id="dd-book-dept-ft-orgDeptOwner"
                            :class="{'valid': !$v.ddBookDept.orgDeptOwner.$invalid, 'invalid': $v.ddBookDept.orgDeptOwner.$invalid }" v-model="$v.ddBookDept.orgDeptOwner.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.deptManagerUseridList')" for="dd-book-dept-ft-deptManagerUseridList">Dept Manager Userid List</label>
                        <input type="text" class="form-control" name="deptManagerUseridList" id="dd-book-dept-ft-deptManagerUseridList"
                            :class="{'valid': !$v.ddBookDept.deptManagerUseridList.$invalid, 'invalid': $v.ddBookDept.deptManagerUseridList.$invalid }" v-model="$v.ddBookDept.deptManagerUseridList.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.sourceIdentifier')" for="dd-book-dept-ft-sourceIdentifier">Source Identifier</label>
                        <input type="text" class="form-control" name="sourceIdentifier" id="dd-book-dept-ft-sourceIdentifier"
                            :class="{'valid': !$v.ddBookDept.sourceIdentifier.$invalid, 'invalid': $v.ddBookDept.sourceIdentifier.$invalid }" v-model="$v.ddBookDept.sourceIdentifier.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.ext')" for="dd-book-dept-ft-ext">Ext</label>
                        <input type="text" class="form-control" name="ext" id="dd-book-dept-ft-ext"
                            :class="{'valid': !$v.ddBookDept.ext.$invalid, 'invalid': $v.ddBookDept.ext.$invalid }" v-model="$v.ddBookDept.ext.$model" />
                    </div>
                    <div class="form-group">
                        <label v-text="$t('ftApp.ddBookDept.ddBookPerson')" for="dd-book-dept-ft-ddBookPerson">Dd Book Person</label>
                        <select class="form-control" id="dd-book-dept-ft-ddBookPerson" multiple name="ddBookPerson" v-model="ddBookDept.ddBookPeople">
                            <option v-bind:value="getSelected(ddBookDept.ddBookPeople, ddBookPersonOption)" v-for="ddBookPersonOption in ddBookPeople" :key="ddBookPersonOption.id">{{ddBookPersonOption.id}}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookDept.ddBookDept')" for="dd-book-dept-ft-ddBookDept">Dd Book Dept</label>
                        <select class="form-control" id="dd-book-dept-ft-ddBookDept" name="ddBookDept" v-model="ddBookDept.ddBookDept">
                            <option v-bind:value="null"></option>
                            <option v-bind:value="ddBookDept.ddBookDept && ddBookDeptOption.id === ddBookDept.ddBookDept.id ? ddBookDept.ddBookDept : ddBookDeptOption" v-for="ddBookDeptOption in ddBookDepts" :key="ddBookDeptOption.id">{{ddBookDeptOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.ddBookDept.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./dd-book-dept-ft-update.component.ts">
</script>
