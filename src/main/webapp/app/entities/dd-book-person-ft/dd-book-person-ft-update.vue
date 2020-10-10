<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ftApp.ddBookPerson.home.createOrEditLabel" v-text="$t('ftApp.ddBookPerson.home.createOrEditLabel')">Create or edit a DdBookPersonFt</h2>
                <div>
                    <div class="form-group" v-if="ddBookPerson.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="ddBookPerson.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.unionid')" for="dd-book-person-ft-unionid">Unionid</label>
                        <input type="text" class="form-control" name="unionid" id="dd-book-person-ft-unionid"
                            :class="{'valid': !$v.ddBookPerson.unionid.$invalid, 'invalid': $v.ddBookPerson.unionid.$invalid }" v-model="$v.ddBookPerson.unionid.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.remark')" for="dd-book-person-ft-remark">Remark</label>
                        <input type="text" class="form-control" name="remark" id="dd-book-person-ft-remark"
                            :class="{'valid': !$v.ddBookPerson.remark.$invalid, 'invalid': $v.ddBookPerson.remark.$invalid }" v-model="$v.ddBookPerson.remark.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.userid')" for="dd-book-person-ft-userid">Userid</label>
                        <input type="text" class="form-control" name="userid" id="dd-book-person-ft-userid"
                            :class="{'valid': !$v.ddBookPerson.userid.$invalid, 'invalid': $v.ddBookPerson.userid.$invalid }" v-model="$v.ddBookPerson.userid.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.isLeaderInDepts')" for="dd-book-person-ft-isLeaderInDepts">Is Leader In Depts</label>
                        <input type="text" class="form-control" name="isLeaderInDepts" id="dd-book-person-ft-isLeaderInDepts"
                            :class="{'valid': !$v.ddBookPerson.isLeaderInDepts.$invalid, 'invalid': $v.ddBookPerson.isLeaderInDepts.$invalid }" v-model="$v.ddBookPerson.isLeaderInDepts.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.isBoss')" for="dd-book-person-ft-isBoss">Is Boss</label>
                        <input type="checkbox" class="form-check" name="isBoss" id="dd-book-person-ft-isBoss"
                            :class="{'valid': !$v.ddBookPerson.isBoss.$invalid, 'invalid': $v.ddBookPerson.isBoss.$invalid }" v-model="$v.ddBookPerson.isBoss.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.hiredDate')" for="dd-book-person-ft-hiredDate">Hired Date</label>
                        <input type="number" class="form-control" name="hiredDate" id="dd-book-person-ft-hiredDate"
                            :class="{'valid': !$v.ddBookPerson.hiredDate.$invalid, 'invalid': $v.ddBookPerson.hiredDate.$invalid }" v-model.number="$v.ddBookPerson.hiredDate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.isSenior')" for="dd-book-person-ft-isSenior">Is Senior</label>
                        <input type="checkbox" class="form-check" name="isSenior" id="dd-book-person-ft-isSenior"
                            :class="{'valid': !$v.ddBookPerson.isSenior.$invalid, 'invalid': $v.ddBookPerson.isSenior.$invalid }" v-model="$v.ddBookPerson.isSenior.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.tel')" for="dd-book-person-ft-tel">Tel</label>
                        <input type="text" class="form-control" name="tel" id="dd-book-person-ft-tel"
                            :class="{'valid': !$v.ddBookPerson.tel.$invalid, 'invalid': $v.ddBookPerson.tel.$invalid }" v-model="$v.ddBookPerson.tel.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.department')" for="dd-book-person-ft-department">Department</label>
                        <input type="text" class="form-control" name="department" id="dd-book-person-ft-department"
                            :class="{'valid': !$v.ddBookPerson.department.$invalid, 'invalid': $v.ddBookPerson.department.$invalid }" v-model="$v.ddBookPerson.department.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.email')" for="dd-book-person-ft-email">Email</label>
                        <input type="text" class="form-control" name="email" id="dd-book-person-ft-email"
                            :class="{'valid': !$v.ddBookPerson.email.$invalid, 'invalid': $v.ddBookPerson.email.$invalid }" v-model="$v.ddBookPerson.email.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.workPlace')" for="dd-book-person-ft-workPlace">Work Place</label>
                        <input type="text" class="form-control" name="workPlace" id="dd-book-person-ft-workPlace"
                            :class="{'valid': !$v.ddBookPerson.workPlace.$invalid, 'invalid': $v.ddBookPerson.workPlace.$invalid }" v-model="$v.ddBookPerson.workPlace.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.orderInDepts')" for="dd-book-person-ft-orderInDepts">Order In Depts</label>
                        <input type="text" class="form-control" name="orderInDepts" id="dd-book-person-ft-orderInDepts"
                            :class="{'valid': !$v.ddBookPerson.orderInDepts.$invalid, 'invalid': $v.ddBookPerson.orderInDepts.$invalid }" v-model="$v.ddBookPerson.orderInDepts.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.mobile')" for="dd-book-person-ft-mobile">Mobile</label>
                        <input type="text" class="form-control" name="mobile" id="dd-book-person-ft-mobile"
                            :class="{'valid': !$v.ddBookPerson.mobile.$invalid, 'invalid': $v.ddBookPerson.mobile.$invalid }" v-model="$v.ddBookPerson.mobile.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.errmsg')" for="dd-book-person-ft-errmsg">Errmsg</label>
                        <input type="text" class="form-control" name="errmsg" id="dd-book-person-ft-errmsg"
                            :class="{'valid': !$v.ddBookPerson.errmsg.$invalid, 'invalid': $v.ddBookPerson.errmsg.$invalid }" v-model="$v.ddBookPerson.errmsg.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.active')" for="dd-book-person-ft-active">Active</label>
                        <input type="checkbox" class="form-check" name="active" id="dd-book-person-ft-active"
                            :class="{'valid': !$v.ddBookPerson.active.$invalid, 'invalid': $v.ddBookPerson.active.$invalid }" v-model="$v.ddBookPerson.active.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.avatar')" for="dd-book-person-ft-avatar">Avatar</label>
                        <input type="text" class="form-control" name="avatar" id="dd-book-person-ft-avatar"
                            :class="{'valid': !$v.ddBookPerson.avatar.$invalid, 'invalid': $v.ddBookPerson.avatar.$invalid }" v-model="$v.ddBookPerson.avatar.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.isAdmin')" for="dd-book-person-ft-isAdmin">Is Admin</label>
                        <input type="checkbox" class="form-check" name="isAdmin" id="dd-book-person-ft-isAdmin"
                            :class="{'valid': !$v.ddBookPerson.isAdmin.$invalid, 'invalid': $v.ddBookPerson.isAdmin.$invalid }" v-model="$v.ddBookPerson.isAdmin.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.isHide')" for="dd-book-person-ft-isHide">Is Hide</label>
                        <input type="checkbox" class="form-check" name="isHide" id="dd-book-person-ft-isHide"
                            :class="{'valid': !$v.ddBookPerson.isHide.$invalid, 'invalid': $v.ddBookPerson.isHide.$invalid }" v-model="$v.ddBookPerson.isHide.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.jobnumber')" for="dd-book-person-ft-jobnumber">Jobnumber</label>
                        <input type="text" class="form-control" name="jobnumber" id="dd-book-person-ft-jobnumber"
                            :class="{'valid': !$v.ddBookPerson.jobnumber.$invalid, 'invalid': $v.ddBookPerson.jobnumber.$invalid }" v-model="$v.ddBookPerson.jobnumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.name')" for="dd-book-person-ft-name">Name</label>
                        <input type="text" class="form-control" name="name" id="dd-book-person-ft-name"
                            :class="{'valid': !$v.ddBookPerson.name.$invalid, 'invalid': $v.ddBookPerson.name.$invalid }" v-model="$v.ddBookPerson.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.extattr')" for="dd-book-person-ft-extattr">Extattr</label>
                        <input type="text" class="form-control" name="extattr" id="dd-book-person-ft-extattr"
                            :class="{'valid': !$v.ddBookPerson.extattr.$invalid, 'invalid': $v.ddBookPerson.extattr.$invalid }" v-model="$v.ddBookPerson.extattr.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.stateCode')" for="dd-book-person-ft-stateCode">State Code</label>
                        <input type="text" class="form-control" name="stateCode" id="dd-book-person-ft-stateCode"
                            :class="{'valid': !$v.ddBookPerson.stateCode.$invalid, 'invalid': $v.ddBookPerson.stateCode.$invalid }" v-model="$v.ddBookPerson.stateCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.position')" for="dd-book-person-ft-position">Position</label>
                        <input type="text" class="form-control" name="position" id="dd-book-person-ft-position"
                            :class="{'valid': !$v.ddBookPerson.position.$invalid, 'invalid': $v.ddBookPerson.position.$invalid }" v-model="$v.ddBookPerson.position.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.roles')" for="dd-book-person-ft-roles">Roles</label>
                        <input type="text" class="form-control" name="roles" id="dd-book-person-ft-roles"
                            :class="{'valid': !$v.ddBookPerson.roles.$invalid, 'invalid': $v.ddBookPerson.roles.$invalid }" v-model="$v.ddBookPerson.roles.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddBookPerson.parentDeptsIdList')" for="dd-book-person-ft-parentDeptsIdList">Parent Depts Id List</label>
                        <input type="text" class="form-control" name="parentDeptsIdList" id="dd-book-person-ft-parentDeptsIdList"
                            :class="{'valid': !$v.ddBookPerson.parentDeptsIdList.$invalid, 'invalid': $v.ddBookPerson.parentDeptsIdList.$invalid }" v-model="$v.ddBookPerson.parentDeptsIdList.$model" />
                    </div>
                    <div class="form-group">
                        <label v-text="$t('ftApp.ddBookPerson.ddBookDept')" for="dd-book-person-ft-ddBookDept">Dd Book Dept</label>
                        <select class="form-control" id="dd-book-person-ft-ddBookDept" multiple name="ddBookDept" v-model="ddBookPerson.ddBookDepts">
                            <option v-bind:value="getSelected(ddBookPerson.ddBookDepts, ddBookDeptOption)" v-for="ddBookDeptOption in ddBookDepts" :key="ddBookDeptOption.id">{{ddBookDeptOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.ddBookPerson.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./dd-book-person-ft-update.component.ts">
</script>
