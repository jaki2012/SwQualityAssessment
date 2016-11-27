import argparse
import sys, warnings, getopt, scipy, datetime, math, random;
import numpy as np
import pickle
from sklearn import svm
from sklearn import datasets
from sklearn.externals import joblib
from sklearn import svm
from sklearn import datasets

clf = svm.SVC()
clf = joblib.load('filename.pkl')
def my_add(list):
    testList = []
    for i in list:
        testList.append(i)
    print testList.__len__()
    nplist = np.array(testList)
    return clf.predict(nplist)[0]

parser = argparse.ArgumentParser(description='Process some integers.')
parser.add_argument('integers', metavar='N', type=float, nargs='+', help='an integer for the accumulator')
parser.add_argument('--ml', dest='accumulate', action='store_const', const=my_add, default=max,
                    help='ml the integers (default: find the max)')

args = parser.parse_args()

print args.accumulate(args.integers)
