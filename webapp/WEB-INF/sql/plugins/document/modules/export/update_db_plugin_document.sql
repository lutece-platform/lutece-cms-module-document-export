--
-- Updating data for table `document_space_action`
--

INSERT INTO `document_space_action` (`id_action`,`name_key`,`description_key`,`action_url`,`icon_url`,`action_permission`) VALUES
(100,'module.document.export.spaces.action.exportSpace.name','module.document.export.spaces.action.exportSpace.description','jsp/admin/plugins/document/modules/export/DoExportDocumentsData.jsp','images/admin/skin/plugins/document/modules/export/export_download.png','EXPORT');