/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights
 *          reserved. For licensing, see LICENSE.md or
 *          http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function(config) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	
	config.font_names='宋体/宋体;黑体/黑体;仿宋/仿宋_GB2312;楷体/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;'+ config.font_names;

	if(!CKEDITOR.plugins.get("productimage")){
		CKEDITOR.plugins.add('productimage', {
			init : function(editor) {
				var pluginName = 'productimage';
				CKEDITOR.dialog.add(pluginName, this.path
						+ 'dialogs/productimage.js');
				editor.addCommand(pluginName,
						new CKEDITOR.dialogCommand(pluginName));
				editor.ui.addButton(pluginName, {
					label : '选择商品图片',
					command : pluginName,
					icon : this.path + 'images/icon.png'
				});
			}
		});
	};

	config.toolbar = 'MyToolbar';
	// remove ['-', 'Templates', 'Flash', 'ShowBlocks', '-', 'About']
	config.toolbar_MyToolbar = [
			[ 'Source', '-', 'Preview', 'Print' ],
			[ 'Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-',
					'SpellChecker', 'Scayt' ],
			[ 'Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll',
					'RemoveFormat' ],
			[ 'Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript',
					'Superscript' ],
			[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent',
					'Blockquote', 'CreateDiv' ],
			[ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
			[ 'productimage', 'Table', 'HorizontalRule', 'Smiley',
					'SpecialChar', 'PageBreak' ],'/',
			[ 'Styles', 'Format', 'Font', 'FontSize' ],
			[ 'TextColor', 'BGColor' ], [ 'Maximize' ] ];

	config.extraPlugins += (config.extraPlugins ? ',productimage'
			: 'productimage');
};
