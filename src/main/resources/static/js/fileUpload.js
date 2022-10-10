const fileUpload  = {
	/**
	* 파일 업로드 처리 
	 */
	process(files) {
		try {
			if (!files || files.length == 0) {
				throw new Error("파일을 업로드 하세요.");
			}
			
			const formData = new FormData();
			for (const file of files) {
				formData.append("file", file);
			}
			
			// 이미지 통제 
			const imageOnlyEl = document.querySelector("input[name='imageOnly']");
			if (imageOnlyEl && imageOnlyEl.value == "true") {
				formData.append("imageOnly", true);
				
				/** 파일 중에서 이미지가 아닌게 포함되어 있는지 체크  */
				for (const file of files) {
					if (file.type.indexOf("image") == -1) { // 이미지가 아닌 파일이 있는 경우
						throw new Error("이미지 형식의 파일을 업로드 하세요.");
					}
				}
			}
			
			// 그룹 ID 
			const gidEl = document.querySelector("input[name='gid']");
			if (gidEl && gidEl.value.trim() != "") {
				formData.append("gid", gidEl.value);
			}
			
			const xhr = new XMLHttpRequest();
			xhr.open("POST", "../file/upload");
			xhr.addEventListener("readystatechange", function() {
				if (xhr.status == 200 && xhr.readyState == XMLHttpRequest.DONE) {
					const data = JSON.parse(xhr.responseText);
					
					if (!data.success && data.message) { // 파일 업로드 실패
						alert(data.message);
						return;
					}
					
					// 파일 업로드 템플릿 처리 
					fileUpload.appendFiles(data);
					console.log(data);
					// 파일 업로드 콜백 처리 
					if (typeof parent.fileUploadCallback == 'function') {
						parent.fileUploadCallback(data);
					}
					
					// 파일 태그 초기화
					const fileEl = document.getElementById("file");
					if (fileEl) fileEl.value = "";
				}
			});
			xhr.onerror = function(err) {
				console.log(err);
				console.log(xhr.responseText);
			};
			xhr.send(formData);
			
		} catch (err) {
			alert(err.message);
		}
	},
	/** 업로드 완료 파일 처리  */
	appendFiles(files) {
		const uploadedFilesEl = document.getElementById("uploaded_files");
		if (!uploadedFilesEl || !files || files.length == 0) {
			return;
		}
		
		const tplEl = document.getElementById("fileListTpl");
		if (!tplEl) return;
		const tpl = tplEl.innerHTML;
		const domParser = new DOMParser();
		for (const file of files) {
			let html = tpl; 
			html = html.replace(/<%=id%>/g, file.id)
							.replace(/<%=fileName%>/g, file.fileName);
							
			const dom = domParser.parseFromString(html, "text/html");
			const liEl = dom.querySelector("li");
			uploadedFilesEl.appendChild(liEl);
			
			const removeEl = liEl.querySelector(".remove");
			if (removeEl) {
				removeEl.addEventListener("click", fileUpload.delete);
			}
		}
	},
	/** 
	* 파일 삭제
	* @param e
	*/
	delete(e) {
		
	}
 };

window.addEventListener("DOMContentLoaded", function() {
	/** 드래그앤 드롭 이벤트 처리 S */
	const dropBoxEl = document.getElementById("drop_box");
	if (dropBoxEl) {
		dropBoxEl.addEventListener("dragover", function(e) {
				e.preventDefault();
		});
		
		dropBoxEl.addEventListener("drop", function(e) {
				e.preventDefault();
				const files = e.dataTransfer.files;
				if (files && files.length > 0) { // 업로드된 파일이 있는 경우 
					fileUpload.process(files);
				}
		});
	}
	/** 드래그앤 드롭 이벤트 처리 E */
	
	/** 파일 태그에서 선택 이벤트 처리 S */
	const fileEl = document.getElementById("file");
	fileEl.addEventListener("change", function(e) {
		const files = e.target.files;
		if (files && files.length > 0) {
			fileUpload.process(files);
		}
	}); 
	/** 파일 태그에서 선택 이벤트 처리 E */
});




