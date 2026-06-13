@echo off
setlocal enabledelayedexpansion

:: ====================================================================
:: CONFIGURATION
:: ====================================================================
set "MC_TEXTURES_DIR=F:\minecraft_modding\mcreator\MC_GlowingAlchemy\src\main\resources\assets\mc_glowingalchemy\textures"
set "DEV_ASSETS_DIR=F:\minecraft_modding\ai_assisted\GlowingAlchemy\src\main\resources\assets"

echo ===================================================
echo   Syncing MCreator Assets to Core + Submods
echo ===================================================
echo.

:: Verify paths
if not exist "%MC_TEXTURES_DIR%" (echo [ERROR] Source missing! && goto :END)
if not exist "%DEV_ASSETS_DIR%" (echo [ERROR] Destination missing! && goto :END)

:: ====================================================================
:: STEP 1: Sync the Explicit Submod Folders
:: ====================================================================
:: This safely copies the dedicated subfolders directly to the submods
for %%S in (glowing_things elemental_alchemy redstone_industry) do (
    if exist "%MC_TEXTURES_DIR%\%%S" (
        echo Syncing submod textures for: %%S...
        if not exist "%DEV_ASSETS_DIR%\%%S\textures" mkdir "%DEV_ASSETS_DIR%\%%S\textures"
        xcopy "%MC_TEXTURES_DIR%\%%S" "%DEV_ASSETS_DIR%\%%S\textures" /E /D /Y /I >nul
    )
)

:: ====================================================================
:: STEP 2: Sync Loose Root Files to Core (glowingalchemy)
:: ====================================================================
:: Instead of copying entire folders, we copy files directly from the root 
:: of 'block' and 'item' so they land in the main core namespace.

echo Syncing core mod textures to glowingalchemy...

:: Handle loose block textures (ignores subfolders like glowing_things\blocks)
if exist "%MC_TEXTURES_DIR%\block" (
    if not exist "%DEV_ASSETS_DIR%\glowingalchemy\textures\block" mkdir "%DEV_ASSETS_DIR%\glowingalchemy\textures\block"
    
    :: Copy only files in the immediate directory, preventing recursive subfolder bleeding
    xcopy "%MC_TEXTURES_DIR%\block\*.png" "%DEV_ASSETS_DIR%\glowingalchemy\textures\block\" /D /Y >nul
    xcopy "%MC_TEXTURES_DIR%\block\*.mcmeta" "%DEV_ASSETS_DIR%\glowingalchemy\textures\block\" /D /Y 2>nul >nul
)

:: Handle loose item textures
if exist "%MC_TEXTURES_DIR%\item" (
    if not exist "%DEV_ASSETS_DIR%\glowingalchemy\textures\item" mkdir "%DEV_ASSETS_DIR%\glowingalchemy\textures\item"
    
    xcopy "%MC_TEXTURES_DIR%\item\*.png" "%DEV_ASSETS_DIR%\glowingalchemy\textures\item\" /D /Y >nul
    xcopy "%MC_TEXTURES_DIR%\item\*.mcmeta" "%DEV_ASSETS_DIR%\glowingalchemy\textures\item\" /D /Y 2>nul >nul
)

:END
echo.
echo Asset sorting complete!
pause