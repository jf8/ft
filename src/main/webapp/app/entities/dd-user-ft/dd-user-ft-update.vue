<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ftApp.ddUser.home.createOrEditLabel" v-text="$t('ftApp.ddUser.home.createOrEditLabel')">Create or edit a DdUserFt</h2>
                <div>
                    <div class="form-group" v-if="ddUser.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="ddUser.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.unionid')" for="dd-user-ft-unionid">Unionid</label>
                        <input type="text" class="form-control" name="unionid" id="dd-user-ft-unionid"
                            :class="{'valid': !$v.ddUser.unionid.$invalid, 'invalid': $v.ddUser.unionid.$invalid }" v-model="$v.ddUser.unionid.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.remark')" for="dd-user-ft-remark">Remark</label>
                        <input type="text" class="form-control" name="remark" id="dd-user-ft-remark"
                            :class="{'valid': !$v.ddUser.remark.$invalid, 'invalid': $v.ddUser.remark.$invalid }" v-model="$v.ddUser.remark.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.userid')" for="dd-user-ft-userid">Userid</label>
                        <input type="text" class="form-control" name="userid" id="dd-user-ft-userid"
                            :class="{'valid': !$v.ddUser.userid.$invalid, 'invalid': $v.ddUser.userid.$invalid }" v-model="$v.ddUser.userid.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.isLeaderInDepts')" for="dd-user-ft-isLeaderInDepts">Is Leader In Depts</label>
                        <input type="text" class="form-control" name="isLeaderInDepts" id="dd-user-ft-isLeaderInDepts"
                            :class="{'valid': !$v.ddUser.isLeaderInDepts.$invalid, 'invalid': $v.ddUser.isLeaderInDepts.$invalid }" v-model="$v.ddUser.isLeaderInDepts.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.isBoss')" for="dd-user-ft-isBoss">Is Boss</label>
                        <input type="checkbox" class="form-check" name="isBoss" id="dd-user-ft-isBoss"
                            :class="{'valid': !$v.ddUser.isBoss.$invalid, 'invalid': $v.ddUser.isBoss.$invalid }" v-model="$v.ddUser.isBoss.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.hiredDate')" for="dd-user-ft-hiredDate">Hired Date</label>
                        <input type="number" class="form-control" name="hiredDate" id="dd-user-ft-hiredDate"
                            :class="{'valid': !$v.ddUser.hiredDate.$invalid, 'invalid': $v.ddUser.hiredDate.$invalid }" v-model.number="$v.ddUser.hiredDate.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.isSenior')" for="dd-user-ft-isSenior">Is Senior</label>
                        <input type="checkbox" class="form-check" name="isSenior" id="dd-user-ft-isSenior"
                            :class="{'valid': !$v.ddUser.isSenior.$invalid, 'invalid': $v.ddUser.isSenior.$invalid }" v-model="$v.ddUser.isSenior.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.tel')" for="dd-user-ft-tel">Tel</label>
                        <input type="text" class="form-control" name="tel" id="dd-user-ft-tel"
                            :class="{'valid': !$v.ddUser.tel.$invalid, 'invalid': $v.ddUser.tel.$invalid }" v-model="$v.ddUser.tel.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.department')" for="dd-user-ft-department">Department</label>
                        <input type="text" class="form-control" name="department" id="dd-user-ft-department"
                            :class="{'valid': !$v.ddUser.department.$invalid, 'invalid': $v.ddUser.department.$invalid }" v-model="$v.ddUser.department.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.workPlace')" for="dd-user-ft-workPlace">Work Place</label>
                        <input type="text" class="form-control" name="workPlace" id="dd-user-ft-workPlace"
                            :class="{'valid': !$v.ddUser.workPlace.$invalid, 'invalid': $v.ddUser.workPlace.$invalid }" v-model="$v.ddUser.workPlace.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.orderInDepts')" for="dd-user-ft-orderInDepts">Order In Depts</label>
                        <input type="text" class="form-control" name="orderInDepts" id="dd-user-ft-orderInDepts"
                            :class="{'valid': !$v.ddUser.orderInDepts.$invalid, 'invalid': $v.ddUser.orderInDepts.$invalid }" v-model="$v.ddUser.orderInDepts.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.mobile')" for="dd-user-ft-mobile">Mobile</label>
                        <input type="text" class="form-control" name="mobile" id="dd-user-ft-mobile"
                            :class="{'valid': !$v.ddUser.mobile.$invalid, 'invalid': $v.ddUser.mobile.$invalid }" v-model="$v.ddUser.mobile.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.errmsg')" for="dd-user-ft-errmsg">Errmsg</label>
                        <input type="text" class="form-control" name="errmsg" id="dd-user-ft-errmsg"
                            :class="{'valid': !$v.ddUser.errmsg.$invalid, 'invalid': $v.ddUser.errmsg.$invalid }" v-model="$v.ddUser.errmsg.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.active')" for="dd-user-ft-active">Active</label>
                        <input type="checkbox" class="form-check" name="active" id="dd-user-ft-active"
                            :class="{'valid': !$v.ddUser.active.$invalid, 'invalid': $v.ddUser.active.$invalid }" v-model="$v.ddUser.active.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.avatar')" for="dd-user-ft-avatar">Avatar</label>
                        <input type="text" class="form-control" name="avatar" id="dd-user-ft-avatar"
                            :class="{'valid': !$v.ddUser.avatar.$invalid, 'invalid': $v.ddUser.avatar.$invalid }" v-model="$v.ddUser.avatar.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.isAdmin')" for="dd-user-ft-isAdmin">Is Admin</label>
                        <input type="checkbox" class="form-check" name="isAdmin" id="dd-user-ft-isAdmin"
                            :class="{'valid': !$v.ddUser.isAdmin.$invalid, 'invalid': $v.ddUser.isAdmin.$invalid }" v-model="$v.ddUser.isAdmin.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.isHide')" for="dd-user-ft-isHide">Is Hide</label>
                        <input type="checkbox" class="form-check" name="isHide" id="dd-user-ft-isHide"
                            :class="{'valid': !$v.ddUser.isHide.$invalid, 'invalid': $v.ddUser.isHide.$invalid }" v-model="$v.ddUser.isHide.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.jobnumber')" for="dd-user-ft-jobnumber">Jobnumber</label>
                        <input type="text" class="form-control" name="jobnumber" id="dd-user-ft-jobnumber"
                            :class="{'valid': !$v.ddUser.jobnumber.$invalid, 'invalid': $v.ddUser.jobnumber.$invalid }" v-model="$v.ddUser.jobnumber.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.name')" for="dd-user-ft-name">Name</label>
                        <input type="text" class="form-control" name="name" id="dd-user-ft-name"
                            :class="{'valid': !$v.ddUser.name.$invalid, 'invalid': $v.ddUser.name.$invalid }" v-model="$v.ddUser.name.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.extattr')" for="dd-user-ft-extattr">Extattr</label>
                        <input type="text" class="form-control" name="extattr" id="dd-user-ft-extattr"
                            :class="{'valid': !$v.ddUser.extattr.$invalid, 'invalid': $v.ddUser.extattr.$invalid }" v-model="$v.ddUser.extattr.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.stateCode')" for="dd-user-ft-stateCode">State Code</label>
                        <input type="text" class="form-control" name="stateCode" id="dd-user-ft-stateCode"
                            :class="{'valid': !$v.ddUser.stateCode.$invalid, 'invalid': $v.ddUser.stateCode.$invalid }" v-model="$v.ddUser.stateCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.position')" for="dd-user-ft-position">Position</label>
                        <input type="text" class="form-control" name="position" id="dd-user-ft-position"
                            :class="{'valid': !$v.ddUser.position.$invalid, 'invalid': $v.ddUser.position.$invalid }" v-model="$v.ddUser.position.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.ddUser.roles')" for="dd-user-ft-roles">Roles</label>
                        <input type="text" class="form-control" name="roles" id="dd-user-ft-roles"
                            :class="{'valid': !$v.ddUser.roles.$invalid, 'invalid': $v.ddUser.roles.$invalid }" v-model="$v.ddUser.roles.$model" />
                    </div>
                    <div class="form-group">
                        <label v-text="$t('ftApp.ddUser.ddBookDept')" for="dd-user-ft-ddBookDept">Dd Book Dept</label>
                        <select class="form-control" id="dd-user-ft-ddBookDept" multiple name="ddBookDept" v-model="ddUser.ddBookDepts">
                            <option v-bind:value="getSelected(ddUser.ddBookDepts, ddBookDeptOption)" v-for="ddBookDeptOption in ddBookDepts" :key="ddBookDeptOption.id">{{ddBookDeptOption.id}}</option>
                        </select>
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.ddUser.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./dd-user-ft-update.component.ts">
</script>
