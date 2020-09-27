<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <form name="editForm" role="form" novalidate v-on:submit.prevent="save()" >
                <h2 id="ftApp.vFtUserSignInfo.home.createOrEditLabel" v-text="$t('ftApp.vFtUserSignInfo.home.createOrEditLabel')">Create or edit a VFtUserSignInfoFt</h2>
                <div>
                    <div class="form-group" v-if="vFtUserSignInfo.id">
                        <label for="id" v-text="$t('global.field.id')">ID</label>
                        <input type="text" class="form-control" id="id" name="id"
                               v-model="vFtUserSignInfo.id" readonly />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.phoneCode')" for="v-ft-user-sign-info-ft-phoneCode">Phone Code</label>
                        <input type="text" class="form-control" name="phoneCode" id="v-ft-user-sign-info-ft-phoneCode"
                            :class="{'valid': !$v.vFtUserSignInfo.phoneCode.$invalid, 'invalid': $v.vFtUserSignInfo.phoneCode.$invalid }" v-model="$v.vFtUserSignInfo.phoneCode.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.phone')" for="v-ft-user-sign-info-ft-phone">Phone</label>
                        <input type="text" class="form-control" name="phone" id="v-ft-user-sign-info-ft-phone"
                            :class="{'valid': !$v.vFtUserSignInfo.phone.$invalid, 'invalid': $v.vFtUserSignInfo.phone.$invalid }" v-model="$v.vFtUserSignInfo.phone.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.email')" for="v-ft-user-sign-info-ft-email">Email</label>
                        <input type="text" class="form-control" name="email" id="v-ft-user-sign-info-ft-email"
                            :class="{'valid': !$v.vFtUserSignInfo.email.$invalid, 'invalid': $v.vFtUserSignInfo.email.$invalid }" v-model="$v.vFtUserSignInfo.email.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.seat')" for="v-ft-user-sign-info-ft-seat">Seat</label>
                        <input type="text" class="form-control" name="seat" id="v-ft-user-sign-info-ft-seat"
                            :class="{'valid': !$v.vFtUserSignInfo.seat.$invalid, 'invalid': $v.vFtUserSignInfo.seat.$invalid }" v-model="$v.vFtUserSignInfo.seat.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.groupIds')" for="v-ft-user-sign-info-ft-groupIds">Group Ids</label>
                        <input type="text" class="form-control" name="groupIds" id="v-ft-user-sign-info-ft-groupIds"
                            :class="{'valid': !$v.vFtUserSignInfo.groupIds.$invalid, 'invalid': $v.vFtUserSignInfo.groupIds.$invalid }" v-model="$v.vFtUserSignInfo.groupIds.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.startTime')" for="v-ft-user-sign-info-ft-startTime">Start Time</label>
                        <div class="d-flex">
                            <input id="v-ft-user-sign-info-ft-startTime" type="datetime-local" class="form-control" name="startTime" :class="{'valid': !$v.vFtUserSignInfo.startTime.$invalid, 'invalid': $v.vFtUserSignInfo.startTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.vFtUserSignInfo.startTime.$model)"
                            @change="updateInstantField('startTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.endTime')" for="v-ft-user-sign-info-ft-endTime">End Time</label>
                        <div class="d-flex">
                            <input id="v-ft-user-sign-info-ft-endTime" type="datetime-local" class="form-control" name="endTime" :class="{'valid': !$v.vFtUserSignInfo.endTime.$invalid, 'invalid': $v.vFtUserSignInfo.endTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.vFtUserSignInfo.endTime.$model)"
                            @change="updateInstantField('endTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.nameCn')" for="v-ft-user-sign-info-ft-nameCn">Name Cn</label>
                        <input type="text" class="form-control" name="nameCn" id="v-ft-user-sign-info-ft-nameCn"
                            :class="{'valid': !$v.vFtUserSignInfo.nameCn.$invalid, 'invalid': $v.vFtUserSignInfo.nameCn.$invalid }" v-model="$v.vFtUserSignInfo.nameCn.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.nameEn')" for="v-ft-user-sign-info-ft-nameEn">Name En</label>
                        <input type="text" class="form-control" name="nameEn" id="v-ft-user-sign-info-ft-nameEn"
                            :class="{'valid': !$v.vFtUserSignInfo.nameEn.$invalid, 'invalid': $v.vFtUserSignInfo.nameEn.$invalid }" v-model="$v.vFtUserSignInfo.nameEn.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.companyCn')" for="v-ft-user-sign-info-ft-companyCn">Company Cn</label>
                        <input type="text" class="form-control" name="companyCn" id="v-ft-user-sign-info-ft-companyCn"
                            :class="{'valid': !$v.vFtUserSignInfo.companyCn.$invalid, 'invalid': $v.vFtUserSignInfo.companyCn.$invalid }" v-model="$v.vFtUserSignInfo.companyCn.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.companyEn')" for="v-ft-user-sign-info-ft-companyEn">Company En</label>
                        <input type="text" class="form-control" name="companyEn" id="v-ft-user-sign-info-ft-companyEn"
                            :class="{'valid': !$v.vFtUserSignInfo.companyEn.$invalid, 'invalid': $v.vFtUserSignInfo.companyEn.$invalid }" v-model="$v.vFtUserSignInfo.companyEn.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.titleCn')" for="v-ft-user-sign-info-ft-titleCn">Title Cn</label>
                        <input type="text" class="form-control" name="titleCn" id="v-ft-user-sign-info-ft-titleCn"
                            :class="{'valid': !$v.vFtUserSignInfo.titleCn.$invalid, 'invalid': $v.vFtUserSignInfo.titleCn.$invalid }" v-model="$v.vFtUserSignInfo.titleCn.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.titleEn')" for="v-ft-user-sign-info-ft-titleEn">Title En</label>
                        <input type="text" class="form-control" name="titleEn" id="v-ft-user-sign-info-ft-titleEn"
                            :class="{'valid': !$v.vFtUserSignInfo.titleEn.$invalid, 'invalid': $v.vFtUserSignInfo.titleEn.$invalid }" v-model="$v.vFtUserSignInfo.titleEn.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.remark')" for="v-ft-user-sign-info-ft-remark">Remark</label>
                        <input type="text" class="form-control" name="remark" id="v-ft-user-sign-info-ft-remark"
                            :class="{'valid': !$v.vFtUserSignInfo.remark.$invalid, 'invalid': $v.vFtUserSignInfo.remark.$invalid }" v-model="$v.vFtUserSignInfo.remark.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.ddid')" for="v-ft-user-sign-info-ft-ddid">Ddid</label>
                        <input type="text" class="form-control" name="ddid" id="v-ft-user-sign-info-ft-ddid"
                            :class="{'valid': !$v.vFtUserSignInfo.ddid.$invalid, 'invalid': $v.vFtUserSignInfo.ddid.$invalid }" v-model="$v.vFtUserSignInfo.ddid.$model" />
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.updateTime')" for="v-ft-user-sign-info-ft-updateTime">Update Time</label>
                        <div class="d-flex">
                            <input id="v-ft-user-sign-info-ft-updateTime" type="datetime-local" class="form-control" name="updateTime" :class="{'valid': !$v.vFtUserSignInfo.updateTime.$invalid, 'invalid': $v.vFtUserSignInfo.updateTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.vFtUserSignInfo.updateTime.$model)"
                            @change="updateInstantField('updateTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.createTime')" for="v-ft-user-sign-info-ft-createTime">Create Time</label>
                        <div class="d-flex">
                            <input id="v-ft-user-sign-info-ft-createTime" type="datetime-local" class="form-control" name="createTime" :class="{'valid': !$v.vFtUserSignInfo.createTime.$invalid, 'invalid': $v.vFtUserSignInfo.createTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.vFtUserSignInfo.createTime.$model)"
                            @change="updateInstantField('createTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.signTime')" for="v-ft-user-sign-info-ft-signTime">Sign Time</label>
                        <div class="d-flex">
                            <input id="v-ft-user-sign-info-ft-signTime" type="datetime-local" class="form-control" name="signTime" :class="{'valid': !$v.vFtUserSignInfo.signTime.$invalid, 'invalid': $v.vFtUserSignInfo.signTime.$invalid }"
                            
                            :value="convertDateTimeFromServer($v.vFtUserSignInfo.signTime.$model)"
                            @change="updateInstantField('signTime', $event)"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="form-control-label" v-text="$t('ftApp.vFtUserSignInfo.meetId')" for="v-ft-user-sign-info-ft-meetId">Meet Id</label>
                        <input type="number" class="form-control" name="meetId" id="v-ft-user-sign-info-ft-meetId"
                            :class="{'valid': !$v.vFtUserSignInfo.meetId.$invalid, 'invalid': $v.vFtUserSignInfo.meetId.$invalid }" v-model.number="$v.vFtUserSignInfo.meetId.$model" />
                    </div>
                </div>
                <div>
                    <button type="button" id="cancel-save" class="btn btn-secondary" v-on:click="previousState()">
                        <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
                    </button>
                    <button type="submit" id="save-entity" :disabled="$v.vFtUserSignInfo.$invalid || isSaving" class="btn btn-primary">
                        <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</template>
<script lang="ts" src="./v-ft-user-sign-info-ft-update.component.ts">
</script>
