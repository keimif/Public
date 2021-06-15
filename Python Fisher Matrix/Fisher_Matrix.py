import numpy as np
import matplotlib.pyplot as plt

# 4a)

# First we load the .covmat file and we reshape it into a 28x28 matrix, stored within another variable.
loadedMatrix = np.loadtxt('base_w_plikHM_TTTEEE_lowl_lowE_BAO_Riess18_Pantheon.covmat', skiprows=1, unpack=True)

#################################################################################################################

# 4b) 

# A function that takes a Covariance matrix and returns it's inverse (Fisher Matrix) 
def linalginv (x):
    y = np.linalg.inv(x)
    return(y)

# Covariance Matrix and Fisher Matrix for omegabh2 and omegach2
print("\n 1) covariance: omegabh2 vs omegach2")
covarianceMatrix1 = loadedMatrix[0:2,0:2]
print(covarianceMatrix1)

print("\n 1) fisher: omegabh2 vs omegach2")
fisherMatrix1 = np.linalg.inv(covarianceMatrix1)
print(fisherMatrix1)

# Covariance Matrix and Fisher Matrix for omegach2 and w
print("\n 2) covariance: omegach2 vs w")
# Locating the 1st, 2nd, 3rd and 4th parameters
topLeft = loadedMatrix[1,1] 
topRight = loadedMatrix[1,4]
bottomLeft = loadedMatrix[4,1]
bottomRight = loadedMatrix[4,4]
#Concatinating parameters together
top = np.concatenate((topLeft, topRight), axis=1)
bottom = np.concatenate((bottomLeft, bottomRight), axis=1)
covarianceMatrix2 = np.concatenate((top, bottom), axis=0)
print(covarianceMatrix2)

#matrix = np.array([loadedMarix[1,1], loadedMarix[1,4]], [loadedMarix[4,1], loadedMarix[4,4]])

print("\n 2) fisher: omegach2 vs w")
fisherMatrix2 = linalginv(covarianceMatrix2)
print(fisherMatrix2)

# Covariance Matrix and Fisher Matrix for LogA and ns
print("\n 3) covariance: LogA vs ns")
covarianceMatrix3 = loadedMatrix[5:7,5:7] 
print(covarianceMatrix3)

print("\n 3) fisher: LogA vs ns")
fisherMatrix3 = linalginv(covarianceMatrix3) 
print(fisherMatrix3)

# Covariance Matrix and Fisher Matrix for tau and w
print("\n 4) covariance: tau vs w")
covarianceMatrix4 = loadedMatrix[3:5,3:5] 
print(covarianceMatrix4)

print("\n 4) fisher: tau vs w")
fisherMatrix4 = linalginv(covarianceMatrix4) 
print(fisherMatrix4)

#################################################################################################################

# 4c)

import math as m

alpha = 1.52

# A function that takes sigmaX, sigmaY, sigmaXY, alpha and returns the width, height and inclination
def ellipse_params(sigmaX, sigmaY, sigmaXY, alpha):
    
    width = (((sigmaX**2) + (sigmaY**2)) / 2) + m.sqrt(((((sigmaX**2) - (sigmaY**2))**2) / 4) + (sigmaXY**2))      
    height = (((sigmaX**2) + (sigmaY**2)) / 2) - m.sqrt(((((sigmaX**2) - (sigmaY**2))**2) / 4) + (sigmaXY**2))   
    inclination = m.atan((2 * sigmaXY) / ((sigmaX**2) - (sigmaY**2)) / 2)
    
    return width, height, inclination

# locating the parameters and printing out the width, height and inclination for omegabh2 vs omegach2
sigmaX1 = m.sqrt(covarianceMatrix1[0,0])
sigmaY1 = m.sqrt(covarianceMatrix1[1,1])
sigmaXY1 = covarianceMatrix1[0,1]

print("\nWidth:", m.sqrt(ellipse_params(sigmaX1, sigmaY1, sigmaXY1, alpha)[0]), "\nHeight:", m.sqrt(ellipse_params(sigmaX1, sigmaY1, sigmaXY1, alpha)[1]), "\nInclination:", ellipse_params(sigmaX1, sigmaY1, sigmaXY1, alpha)[2])

# locating the parameters and printing out the width, height and inclination for omegach2 vs w
sigmaX2 = m.sqrt(covarianceMatrix2[0,0])
sigmaY2 = m.sqrt(covarianceMatrix2[1,1])
sigmaXY2 = covarianceMatrix2[0,1]

print("\nWidth:", m.sqrt(ellipse_params(sigmaX2, sigmaY2, sigmaXY2, alpha)[0]), "\nHeight:", m.sqrt(ellipse_params(sigmaX2, sigmaY2, sigmaXY2, alpha)[1]), "\nInclination:", ellipse_params(sigmaX2, sigmaY2, sigmaXY2, alpha)[2])

# locating the parameters and printing out the width, height and inclination for LogA vs ns
sigmaX3 = m.sqrt(covarianceMatrix3[0,0])
sigmaY3 = m.sqrt(covarianceMatrix3[1,1])
sigmaXY3 = covarianceMatrix3[0,1]

print("\nWidth:", m.sqrt(ellipse_params(sigmaX3, sigmaY3, sigmaXY3, alpha)[0]), "\nHeight:", m.sqrt(ellipse_params(sigmaX3, sigmaY3, sigmaXY3, alpha)[1]), "\nInclination:", ellipse_params(sigmaX3, sigmaY3, sigmaXY3, alpha)[2])

# locating the parameters and printing out the width, height and inclination for tau vs w
sigmaX4 = m.sqrt(covarianceMatrix4[0,0])
sigmaY4 = m.sqrt(covarianceMatrix4[1,1])
sigmaXY4 = covarianceMatrix4[0,1]

print("\nWidth:", m.sqrt(ellipse_params(sigmaX4, sigmaY4, sigmaXY4, alpha)[0]), "\nHeight:", m.sqrt(ellipse_params(sigmaX4, sigmaY4, sigmaXY4, alpha)[1]), "\nInclination:", ellipse_params(sigmaX4, sigmaY4, sigmaXY4, alpha)[2])